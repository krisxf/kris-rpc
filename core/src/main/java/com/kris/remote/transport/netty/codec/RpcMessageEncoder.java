package com.kris.remote.transport.netty.codec;

import com.kris.compress.Compress;
import com.kris.enums.CompressTypeEnum;
import com.kris.enums.SerializationTypeEnum;
import com.kris.extension.ExtensionLoader;
import com.kris.remote.constant.RpcConstants;
import com.kris.remote.dto.RpcMessage;
import com.kris.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-16 15:42
 **/

@Slf4j
public class RpcMessageEncoder extends MessageToByteEncoder<RpcMessage> {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcMessage rpcMessage, ByteBuf out) {
        try {
            // 魔数
            out.writeBytes(RpcConstants.MAGIC_NUMBER);
            // 版本
            out.writeByte(RpcConstants.VERSION);
            // 留出一个地方写全长的值
            out.writerIndex(out.writerIndex() + 4);
            byte messageType = rpcMessage.getMessageType();
            // 消息类型
            out.writeByte(messageType);
            // 序列化方式
            out.writeByte(rpcMessage.getCodec());
            // 压缩方式
            out.writeByte(CompressTypeEnum.GZIP.getCode());
            // 自动增长的id
            out.writeInt(ATOMIC_INTEGER.getAndIncrement());
            // 构建全长
            byte[] bodyBytes = null;
            int fullLength = RpcConstants.HEAD_LENGTH;
            // 如果messageType不是心跳消息，则fullLength=头部长度+正文长度
            if (messageType != RpcConstants.HEARTBEAT_REQUEST_TYPE
                    && messageType != RpcConstants.HEARTBEAT_RESPONSE_TYPE) {
                // 序列化对象
                String codecName = SerializationTypeEnum.getName(rpcMessage.getCodec());
                log.info("codec name: [{}] ", codecName);
                Serializer serializer = ExtensionLoader.getExtensionLoader(Serializer.class)
                        .getExtension(codecName);
                bodyBytes = serializer.serialize(rpcMessage.getData());
                // 压缩字节
                String compressName = CompressTypeEnum.getName(rpcMessage.getCompress());
                Compress compress = ExtensionLoader.getExtensionLoader(Compress.class)
                        .getExtension(compressName);
                bodyBytes = compress.compress(bodyBytes);
                fullLength += bodyBytes.length;
            }

            if (bodyBytes != null) {
                out.writeBytes(bodyBytes);
            }
            int writeIndex = out.writerIndex();
            out.writerIndex(writeIndex - fullLength + RpcConstants.MAGIC_NUMBER.length + 1);
            out.writeInt(fullLength);
            out.writerIndex(writeIndex);
        } catch (Exception e) {
            log.error("Encode request error!", e);
        }

    }


}
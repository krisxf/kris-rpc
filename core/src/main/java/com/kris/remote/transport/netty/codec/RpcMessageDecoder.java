package com.kris.remote.transport.netty.codec;

import com.kris.compress.Compress;
import com.kris.enums.CompressTypeEnum;
import com.kris.enums.SerializationTypeEnum;
import com.kris.extension.ExtensionLoader;
import com.kris.remote.constant.RpcConstants;
import com.kris.remote.dto.RpcMessage;
import com.kris.remote.dto.RpcRequest;
import com.kris.remote.dto.RpcResponse;
import com.kris.serialize.Serializer;
import com.kris.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-16 15:41
 **/

@Slf4j
public class RpcMessageDecoder extends LengthFieldBasedFrameDecoder {
    public RpcMessageDecoder() {
        // lengthFieldOffset：魔术码是4B，版本是1B，然后是全长。所以值是5
        // length字段长度：全长为4B。所以值是4
        // lengthAdjustment：全长包含所有数据，并读取9个字节之前的数据，因此左侧长度为（fullLength-9）。所以值是-9
        // initialBytesToStrip：我们将手动检查魔术代码和版本，所以不要删除任何字节。所以值为0
        this(RpcConstants.MAX_FRAME_LENGTH, 5, 4, -9, 0);
    }

    /**
     * @param maxFrameLength      最大帧长。它决定了可以接收的最大数据长度。
     *                            如果超过，数据将被丢弃。
     * @param lengthFieldOffset   长度字段偏移。长度字段是跳过指定字节长度的字段。
     * @param lengthFieldLength   长度字段中的字节数。
     * @param lengthAdjustment    要添加到长度字段值的补偿值
     * @param initialBytesToStrip 跳过的字节数。
     *                            如果需要接收所有标头+正文数据，则此值为0
     *                            如果你只想接收正文数据，那么你需要跳过标头消耗的字节数。
     */
    public RpcMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                             int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        Object decoded = super.decode(ctx, in);
        if (decoded instanceof ByteBuf) {
            ByteBuf frame = (ByteBuf) decoded;
            if (frame.readableBytes() >= RpcConstants.TOTAL_LENGTH) {
                try {
                    return decodeFrame(frame);
                } catch (Exception e) {
                    log.error("解码错误!", e);
                    throw e;
                } finally {
                    frame.release();
                }
            }

        }
        return decoded;
    }


    private Object decodeFrame(ByteBuf in) {
        // 注意：必须按顺序阅读ByteBuf
        checkMagicNumber(in);
        checkVersion(in);
        int fullLength = in.readInt();
        // 构建RpcMessage对象
        byte messageType = in.readByte();
        byte codecType = in.readByte();
        byte compressType = in.readByte();
        int requestId = in.readInt();
        RpcMessage rpcMessage = RpcMessage.builder()
                .codec(codecType)
                .requestId(requestId)
                .messageType(messageType).build();
        if (messageType == RpcConstants.HEARTBEAT_REQUEST_TYPE) {
            rpcMessage.setData(RpcConstants.PING);
            return rpcMessage;
        }
        if (messageType == RpcConstants.HEARTBEAT_RESPONSE_TYPE) {
            rpcMessage.setData(RpcConstants.PONG);
            return rpcMessage;
        }
        int bodyLength = fullLength - RpcConstants.HEAD_LENGTH;
        if (bodyLength > 0) {
            byte[] bs = new byte[bodyLength];
            in.readBytes(bs);
            // 解压缩字节
            String compressName = CompressTypeEnum.getName(compressType);
                Compress compress = ExtensionLoader.getExtensionLoader(Compress.class)
                    .getExtension(compressName);
            bs = compress.decompress(bs);
            // 反序列化对象
            String codecName = SerializationTypeEnum.getName(rpcMessage.getCodec());
            log.info("序列化名称: [{}] ", codecName);
            Serializer serializer = ExtensionLoader.getExtensionLoader(Serializer.class)
                    .getExtension(codecName);
            if (messageType == RpcConstants.REQUEST_TYPE) {
                RpcRequest tmpValue = serializer.deserialize(bs, RpcRequest.class);
                rpcMessage.setData(tmpValue);
                LogUtil.log(tmpValue.getRequestId(),"反序列化成功！反序列化算法：" + codecName , LocalDateTime.now());
            } else {
                RpcResponse tmpValue = serializer.deserialize(bs, RpcResponse.class);
                rpcMessage.setData(tmpValue);
                LogUtil.log(tmpValue.getRequestId(),"反序列化成功！反序列化算法：" + codecName , LocalDateTime.now());
            }
        }
        return rpcMessage;

    }

    private void checkVersion(ByteBuf in) {
        // 阅读版本并进行比较
        byte version = in.readByte();
        if (version != RpcConstants.VERSION) {
            throw new RuntimeException("版本不匹配" + version);
        }
    }

    private void checkMagicNumber(ByteBuf in) {
        // 读取前4位，即幻数，并进行比较
        int len = RpcConstants.MAGIC_NUMBER.length;
        byte[] tmp = new byte[len];
        in.readBytes(tmp);
        for (int i = 0; i < len; i++) {
            if (tmp[i] != RpcConstants.MAGIC_NUMBER[i]) {
                throw new IllegalArgumentException("不知道的魔数: " + Arrays.toString(tmp));
            }
        }
    }

}

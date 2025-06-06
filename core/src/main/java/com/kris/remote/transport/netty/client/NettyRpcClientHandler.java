package com.kris.remote.transport.netty.client;

import com.kris.enums.CompressTypeEnum;
import com.kris.enums.SerializationTypeEnum;
import com.kris.factory.SingletonFactory;
import com.kris.remote.constant.RpcConstants;
import com.kris.remote.dto.RpcMessage;
import com.kris.remote.dto.RpcResponse;
import com.kris.util.LogUtil;
import com.kris.util.TraceContext;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-16 15:40
 **/

@Slf4j
public class NettyRpcClientHandler extends ChannelInboundHandlerAdapter {
    private final UnprocessedRequests unprocessedRequests;
    private final NettyRpcClient nettyRpcClient;

    public NettyRpcClientHandler() {
        this.unprocessedRequests = SingletonFactory.getInstance(UnprocessedRequests.class);
        this.nettyRpcClient = SingletonFactory.getInstance(NettyRpcClient.class);
    }

    /**
     * 读取服务器发送的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            log.info("服务端返回信息: [{}]", msg);
            if (msg instanceof RpcMessage) {
                RpcMessage tmp = (RpcMessage) msg;
                byte messageType = tmp.getMessageType();
                if (messageType == RpcConstants.HEARTBEAT_RESPONSE_TYPE) {
                    log.info("[HEARTBEAT] 心跳 [{}]", tmp.getData());
                } else if (messageType == RpcConstants.RESPONSE_TYPE) {
                    RpcResponse<Object> rpcResponse = (RpcResponse<Object>) tmp.getData();
                    unprocessedRequests.complete(rpcResponse);
                    LogUtil.log(TraceContext.getTraceId(),"服务端返回信息接收成功！" , LocalDateTime.now());
                }
            }
        } finally {
            ReferenceCountUtil.release(msg);
            TraceContext.clear();
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.WRITER_IDLE) {
                log.info("[HEARTBEAT] 心跳检测发生 [{}]", ctx.channel().remoteAddress());
                Channel channel = nettyRpcClient.getChannel((InetSocketAddress) ctx.channel().remoteAddress());
                RpcMessage rpcMessage = new RpcMessage();
                rpcMessage.setCodec(SerializationTypeEnum.PROTOSTUFF.getCode());
                rpcMessage.setCompress(CompressTypeEnum.GZIP.getCode());
                rpcMessage.setMessageType(RpcConstants.HEARTBEAT_REQUEST_TYPE);
                rpcMessage.setData(RpcConstants.PING);
                channel.writeAndFlush(rpcMessage).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 当处理客户端消息时发生异常时调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("客户端异常捕获：", cause);
        cause.printStackTrace();
        ctx.close();
    }

}

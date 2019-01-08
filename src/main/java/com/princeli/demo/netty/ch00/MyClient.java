package com.princeli.demo.netty.ch00;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: netty-demo
 * @description: ${description}
 * @author: ly
 * @create: 2019-01-08 14:34
 **/
@Slf4j
public class MyClient extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("client receive message:"+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("get client exception :"+cause.getMessage());
    }
}

package com.princeli.demo.netty.ch01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @program: netty-demo
 * @description: ${description}
 * @author: ly
 * @create: 2019-01-09 09:41
 **/
public class Client {
    public static void main(String[] args) throws Exception {
        new Client().run1();
    }


    public void run1() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            Channel ch = b.connect("127.0.0.1",8888).sync().channel();
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(System.in));
            for (;;) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }

                lastWriteFuture = ch.writeAndFlush(line + "\r\n");
                if ("bye".equals(line.toLowerCase())) {
                    ch.closeFuture().sync();
                    break;
                }
            }
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            Channel channel = bootstrap.connect("127.0.0.1", 8888).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                channel.writeAndFlush(in.readLine() + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}



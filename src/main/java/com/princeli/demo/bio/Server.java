package com.princeli.demo.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: netty-demo
 * @description: BIO服务端源码
 * @author: ly
 * @create: 2018-12-26 13:51
 **/
@Slf4j
public class Server {
    /**
     * 默认端口号
     */
    private static int DEFAULT_PORT = 7777;

    /**
     * 单列ServerSocket
     */
    private static ServerSocket serverSocket;
    
    
    public static void start() throws IOException{
        //
        start(DEFAULT_PORT);
    }

    private synchronized static void start(int port) throws IOException {
        if (serverSocket !=null) {
            return;
        }

        try {
            serverSocket = new ServerSocket(port);
            log.info("服务端已启动,端口号:"+port);

            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        }finally {
            if (serverSocket != null){
                log.info("服务端已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }

}

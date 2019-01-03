package com.princeli.demo.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @program: netty-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-12-26 14:03
 **/
@Slf4j
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression;
            String result;
            while (true){
                if ((expression = in.readLine()) == null){
                    break;
                }
                log.info("服务端收到信息:" + expression);

                result = Calculator.cal(expression);
                out.println(result);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (out != null){
                out.close();
                out = null;
            }

            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }

        }

    }
}

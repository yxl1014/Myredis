package com.company;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("localhost",6379);
            InputStream in=socket.getInputStream();
            OutputStream out=socket.getOutputStream();
            byte[] bytes=new byte[1024];
            out.write("*2\r\n$3\r\nget\r\n$1\r\na\r\n".getBytes());
            out.flush();
            in.read(bytes);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MyRedis {
    private HashMap<String,String> cache=new HashMap<>();
    public void Redis(){
        try {
            ServerSocket redis=new ServerSocket(6379);
            while(true){
                Socket socket=redis.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream in=socket.getInputStream();
                            OutputStream out=socket.getOutputStream();
                            byte[] bytes=new byte[1024];
                            int len=in.read(bytes);
                            if(len<=0){
                                out.write("hehe".getBytes());
                                out.flush();
                            }else{
                                String[] s=HaveBody(new String(bytes));
                                String[] ss=IsOk(s);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] IsOk(String[] s) {
        String[] ret=new String[20];
        int sl=0;
        char[] c=s[0].toCharArray();
        int len=c.length;
        for(int i=1;i<len;i++){
            c[i-1]=c[i];
        }
        int sum=new Integer(String.valueOf(c,0,len-1));
        if(sum*2==s.length-1){
            for(int i=1;i<=sum*2;i+=2){
                int l=IsnOk(s[i]);
                if(l!=-1){
                    int ok=IsBodyOk(l,s[i+1]);
                    if(ok!=-1){
                        ret[sl]=s[i+1];
                        sl++;
                    }else{
                        ret[0]="+ERROR";
                        return ret;
                    }
                }else{
                    ret[0]="+ERROR";
                    return ret;
                }
            }
        }else{
            ret[0]="+ERROR";
            return ret;
        }
        ret[0]="+ERROR";
        return ret;
    }

    private int IsBodyOk(int l, String s) {
        if(s.length()==l){
            return 1;
        }else {
            return -1;
        }
    }

    private int IsnOk(String s) {
        char[] c=s.toCharArray();
        if (c[0] == '$') {
            int len=c.length;
            for(int i=1;i<len;i++){
                c[i-1]=c[i];
            }
            int sum=new Integer(String.valueOf(c,0,len-1));
            return sum;
        }
        return -1;
    }

    private String[] HaveBody(String str){
        String[] strings=str.split("\r\n");
        return strings;
    }
}

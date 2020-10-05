package com.company;

public class Test {
    public static void main(String[] args) {
        String str="*6";
        char[] c=str.toCharArray();
        int len=c.length;
        for(int i=1;i<len;i++){
            c[i-1]=c[i];
        }
        int j=new Integer(String.valueOf(c,0,len-1));
        System.out.println(j);
    }
}

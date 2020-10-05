package com.company;

public class Test {

    private static String[] IsOk(String[] s) {
        int sl=0;
        char[] c=s[0].toCharArray();
        int len=c.length;
        for(int i=1;i<len;i++){
            c[i-1]=c[i];
        }
        int sum=new Integer(String.valueOf(c,0,len-1));
        String[] ret=new String[sum];
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
        return ret;
    }

    private static int IsBodyOk(int l, String s) {
        if(s.length()==l){
            return 1;
        }else {
            return -1;
        }
    }

    private static int IsnOk(String s) {
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

    private static String[] HaveBody(String str){
        String[] strings=str.split("\r\n");
        return strings;
    }
    public static void main(String[] args) {
        String bytes="*3\r\n$3\r\nset\r\n$2\r\naa\r\n$2\r\nbb\r\n";
        String[] sss=HaveBody(bytes);
        String[] ss=IsOk(sss);
        for(String s:ss){
            System.out.print(s+" ");
        }
        System.out.println(ss.length);
    }
}

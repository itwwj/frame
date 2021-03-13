package com.gitee.design.prototype;

import java.util.Random;

/**
 * @author jie
 */
public class PrototypeMain {

    private static int MAX_COUNT = 10;

    public static void main(String[] args) throws CloneNotSupportedException {

        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("xxxx版权所有");
        for (int i=0;i<MAX_COUNT;i++){
            Mail clone = mail.clone();
            clone.setApplication(getRandString(5)+"先生/女士");
            clone.setReceiver(getRandString(5)+"@qq.com");
            sendMail(clone);
        }
    }
    public static void sendMail(Mail mail){
        System.out.println("标题： "+mail.getSubject() + "\t收件人："+mail.getReceiver()+"t...发送成功！ ");
    }
    public static String getRandString(int maxLength){
        String source ="abcdefghijklmnopqrskuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for(int i=0;i<maxLength;i++){
            sb.append(source.charAt(rand.nextInt(source.length())));
        }
        return sb.toString();
    }
}

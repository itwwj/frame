package com.gitee.jk;


/**
 * @author jie
 */
public class TestJava {
    public static void main(String[] args) {
        String s = new StringBuilder("ja").append("va").toString();
        System.out.println(s == s.intern());
    }



}

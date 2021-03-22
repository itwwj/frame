package com.gitee.gc;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author jie
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {


        //hashMap();
        weakHash();
    }

    private static void weakHash() {
        WeakHashMap<String, String> map = new WeakHashMap<>();
        String key = "key";
        String value = "value";
        map.put(key, value);

        System.out.println(map);

        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }

    /**
     * 弱hashMap引用
     */
    private static void hashMap() {
        HashMap<String, String> map = new HashMap<>();
        String key = "key";
        String value = "value";
        map.put(key, value);

        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }
}

package com.gitee.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author jie
 */
public class GcDemo {
    public static void main(String[] args) {
        //soft();
        weak();
    }
    /**
     * 软引用
     * 内存够用就保留不够用就回收
     */
    public static void soft() {
        Object o = new Object();
        SoftReference<Object> reference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(reference.get());
        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(reference.get());

    }

    /**
     * 弱引用
     * 只要有gc就会被回收
     */
    public static void weak(){
        Object o = new Object();
        WeakReference<Object> reference = new WeakReference<>(o);
        System.out.println(o);
        System.out.println(reference.get());
        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(reference.get());
    }

    /**
     * 虚引用
     * 如果一个对象仅持有虚引用,那么它就和没有任何引用一样,在任何时候都可能被垃圾回收器回收,
     * 它不能单独使用也不能通过它访问"对象,虚引用必须和引期队列(ReferenceQueue)联合使用。
     * 虚引用的主要作用是跟踪对象被垃圾回收的状态。仅仅是提供了一种确保对象被finalize以后,做某些事情的机制。
     * .PhantomReference的get方法总是返回null,因此无法访问对应的引用对象。
     * 其意义在于说明一个对象已经进入finalizatin阶段,可以被gc回收,用来实现比finalization机制更灵活的回收操作.
     *
     *
     */
    public static void phantom(){

    }
}

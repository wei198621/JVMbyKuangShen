package com.tiza.leo.jvm;

import java.awt.*;
import java.util.Random;

/**
 * @author leowei
 * @date 2021/1/2  - 9:32
 */
public class test01 {
    public static void main(String[] args) throws ClassNotFoundException {
        //m1();
       // m2Native();
        m3outOfMemoryTest();

        //getMemory();

        return;
    }

    private static void getMemory() {
        long l = Runtime.getRuntime().maxMemory();
        System.out.println("runtime Memory = " + l/1024/1024);
        long total = Runtime.getRuntime().totalMemory();
        System.out.println("runtime total total = " + total/1024/1024);
        //当前主机内存   = 16 G
        //runtime Memory =      3611     1/4     分配总内存为计算机的1/4
        //runtime total total = 243      1/64   分配初始内存为计算机的1/64
        // -Xms1024m -Xmx2048m -XX:+PrintGCDetails    --- 放到idea  VM options 里面  ,设置此参数后，打印如下内容
        //-Xms: the initial heap size    -Xmx: the maximum heap size
        // 305664K +  699392K= 1,005,056K / 1024 = 981.5 m
    }

    //
    //        // -Xms8m -Xmx8m -XX:+PrintGCDetails    --- 放到idea  VM options 里面  很小的堆空间，让其很快堆溢出   java.lang.OutOfMemoryError
    private static void m3outOfMemoryTest() {
        String str="leoStudingJava11111111111111111888888888888888888888999999999999997777777777777776666666666";
        while (true){
            str+= str + new Random().nextInt(88884444)+new Random().nextInt(2000011114);
        }
    }

    private static void m2Native() {
        new Thread(()->{
            System.out.println("sss");
        }).start();   //start()  方法 调用 start0()  方法就是本地方法
    }

    // native : java native 关键字，java的作用范围达不到了，调用底层的c语言类库
    //          会进入本地方法栈，
    //          调用本地方法接口   JNI  (java native Interface )
    //          JNI的作用： 口占JAVA的应用，融合不同的编程语言为java所用,最初 c c++程序较多
    //          它在内存区域中专门开启了一块标记区域，Native Method  Stack ，登记native方法
    //           使用示例：   Java程序启动打印机，等 。。。
    //
    // 1         现在调用其他接口的方式有：  Socket  WebService   restful ...

    private native void start();

    private static void m1() throws ClassNotFoundException {
        System.out.println(Person.class);

        Person person = new Person();
        System.out.println(person.getClass());

        Class<?> perCls = Class.forName("com.tiza.leo.jvm.Person");
        System.out.println(perCls);

        ClassLoader classLoader = perCls.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
        System.out.println(classLoader.getParent().getParent().getParent());
    }
}

class Person{
    int age;
    String name;
    boolean sex;
}

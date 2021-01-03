package com.tiza.leo.jvm;

import java.util.ArrayList;

/**
 * @author leowei
 * @date 2021/1/2  - 18:48
 *
 *
 * Dump
 * -Xms10m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 */
public class test03 {
    byte[] array =  new byte[1*1024*1024];

    public static void main(String[] args) {
        ArrayList<Person2> list = new ArrayList<>();
        int count=0;
        try {
            while(true){    //while  当   true  对     ， 一直做下面的操作，
                list.add(new Person2());
                count +=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(count);
        }
        //Throwable
        //     Error           java.lang.OutOfMemoryError  是 Error 不是exception
        //     Exception


    }
}

class Person2{
    private int age;
    private String name;

}

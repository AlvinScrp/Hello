package com.a.robust;

import com.meituan.robust.patch.annotaion.Add;
import com.meituan.robust.patch.annotaion.Modify;

//public class M {
//    private int apple = 3;
//
//    @Modify
//    public String a(int appleNum) {
//        this.apple = appleNum + 1;
//        return "M aaa fix ,apple:" + this.apple;
//    }
//
//    public static int b() {
//        System.out.println("method b ");
//        return 1;
//    }
//
//    @Add
//    public static int c() {
//        System.out.println("method c ");
//        return 1;
//    }
//}

public class M {
    private int apple = 3;

    public String a(int appleNum) {
        this.apple = appleNum ;
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("M aaa,apple:");
        stringBuilder.append(this.apple);
        return stringBuilder.toString();
    }

    public static int b() {
        System.out.println("method b ");
        return 1;
    }
}

//public  class M {
//    public static String a(){
//        return  "M aaa patch";
//    }
//}


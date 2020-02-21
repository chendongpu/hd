package com.example.demo.util;

import java.util.UUID;

public class OrderIdUtil {

    public static String getOrderIdByUUId(String machineId) {

        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String orderId=machineId + String.format("%015d", hashCodeV);
        System.out.println(orderId);
        return orderId;
    }

}

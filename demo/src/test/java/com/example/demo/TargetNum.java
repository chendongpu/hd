package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TargetNum {


    public static Integer getNumberThree(Integer[] array,Integer number){
        int index = Math.abs(number-array[0]);
        int result = array[0];
        for (int i : array) {
            int abs = Math.abs(number-i);
            if(abs <= index){
                index = abs;
                result = i;
            }
        }
        return result;
    }

    @Test
    public void test(){
        Integer[] array = new Integer[] { 1, 2, 5, 10, 30, 60, 120, 240, 770, 1440 };
        Integer target = TargetNum.getNumberThree(array,20);
        log.info("target:{}",target);
    }

}

package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Slf4j
public class CompareArray {

    @Test
    public void test_string(){
        String [] array1 = {"1","2","3"};
        String [] array2 = {"3","2","1"};
        Arrays.sort(array1);
        Arrays.sort(array2);
        if (Arrays.equals(array1, array2)) {
            System.out.println("两个数组中的元素值相同");
        } else {
            System.out.println("两个数组中的元素值不相同");
        }
    }


    @Test
    public void test_long(){
        Long[] array1 = {1l,2l,3l};
        Long[] array2 = {3l,2l,1l};
        Arrays.sort(array1);
        Arrays.sort(array2);
        if (Arrays.equals(array1, array2)) {
            System.out.println("两个数组中的元素值相同");
        } else {
            System.out.println("两个数组中的元素值不相同");
        }
    }
}

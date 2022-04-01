package com.atguigu.boot.studytest.test316;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListExample {
    public static void main(String[] args) {

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8, 8));
        ArrayList<String> numbersList3 = new ArrayList<>(Arrays.asList("a","a","b","b","c"));


        List<String> result = new ArrayList<String>(numbersList3.size());
        for (String str : numbersList3) {
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        numbersList3.clear();
        numbersList3.addAll(result);

        System.out.println("contains方法去重" + numbersList3);

        System.out.println(numbersList);

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(numbersList);

        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);

        System.out.println(listWithoutDuplicates);

        System.out.println("使用 java8 新特性 stream 进行 integer List 去重");
        ArrayList<Integer> numbersList2 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(numbersList2);
        List<Integer> listWithoutDuplicates2 = numbersList2.stream().distinct().collect(Collectors.toList());
        System.out.println(listWithoutDuplicates2);

        System.out.println("使用 java8 新特性 stream 进行 String List 去重");

//        ArrayList<String> numbersList3 = new ArrayList<>(Arrays.asList("a","a","b","b","c"));
        List<String> listWithoutDuplicates3 = numbersList3.stream().distinct().collect(Collectors.toList());
        System.out.println(listWithoutDuplicates3);

    }
}
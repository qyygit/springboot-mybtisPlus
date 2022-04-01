package com.atguigu.boot.stream;

import com.atguigu.boot.bean.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/13 10:36
 **/
public class Java8Stream {
    public static void main(String[] args) {


        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        //遍历,获取符合条件的
        List<Integer> collect1 = list.parallelStream().filter(p -> p > 7).collect(Collectors.toList());
        for (int i = 0; i < collect1.size(); i++) {
            System.out.println(collect1.get(i));
            System.out.println("88888888888888888888");
        }

        //获取第一个匹配值
        Optional<Integer> findFirst = list.stream().filter(p -> p > 8).findFirst();
        Integer result = findFirst.get();
        System.out.println("获取第一个满足条件结果" + result);

        // 使用并行流, 匹配任意满足条件的值
        Optional<Integer> first = list.stream().parallel().filter(p -> p > 6).findAny();
        Integer parallelResult = first.get();
        System.out.println("并行流,匹配任意值" + parallelResult);

        //获取是否包含符合条件的元素
        boolean anyMatch = list.stream().anyMatch(p -> p.equals(6));
        System.out.println("是否存在大于6的值：" + anyMatch);

        //  筛选员工中工资高于8000的人，并形成新的集合。 形成新集合依赖collect（收集），后文有详细介绍。


        List<String> collect = personList.stream().filter(p -> p.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println("工资大于8000的名单" + " - " + collect);

        //String 类型
        List<String> list2 = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list2.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());

        //Integer 类型
        List<Integer> listIntger = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max1 = listIntger.stream().max(Integer::compareTo);
        System.out.println("自然排序的最大值：" + max1.get());

        //自定义排序
        Optional<Integer> max2 = listIntger.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println("自定义排序的最大值：" + max2.get());

        //案例三：获取员工工资最高的人。
        Optional<Person> max3 = personList.stream().max(Comparator.comparing(Person::getSalary));
        System.out.println("员工工资最大值：" + max3.get().getName() + "的" + max3.get().getSalary());

        //案例四：计算Integer集合中大于6的元素的个数。
        List<Integer> list6 = Arrays.asList(7, 6, 4, 8, 2, 11, 9);

        long count = list6.stream().filter(p -> p > 6).count();
        System.out.println("list中大于6的元素个数：" + count);

        //--------------------映射(map/flatMap)  可以将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap：

        //案例一：英文字符串数组的元素全部改为大写。整数数组每个元素+3。
//        小写转大写
        String[] strArr1 = {"abcd", "bcdd", "defde", "fTr"};
        List<String> strList1 = Arrays.stream(strArr1).map(String::toUpperCase).collect(Collectors.toList());

//        大写转小写
        String[] strArr2 = {"AB", "DSA", "FSA", "FSADAS"};
        List<String> strList2 = Arrays.stream(strArr2).map(String::toLowerCase).collect(Collectors.toList());
//        每个元素加3
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

        System.out.println("每个元素转大写：" + strList1);
        System.out.println("每个元素转小写：" + strList2);
        System.out.println("每个元素+3：" + intListNew);

//        -------------------------------------------------------------------------------------------------
        //案例二：将员工的薪资全部增加1000。


        // 不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 1000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());

        // 改变原来员工集合的方式
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前：" + personListNew2.get(0).getName() + "-->" + personListNew2.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew2.get(0).getSalary());

//        -------------------------------------------------------------------------------------------------

        //案例三：将两个字符数组合并成一个新的字符数组。
        List<String> list7 = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list7.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());

        System.out.println("处理前的集合：" + list7);
        System.out.println("处理后的集合：" + listNew);


//        -------------------------------------------------------------------------------------------------

        //归约(reduce) 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
        List<Integer> list8 = Arrays.asList(1, 2, 3, 4);

        Optional<Integer> max4 = list8.stream().max(Integer::compareTo);
        System.out.println("不同实现方式" + max4.get());

        // 求和方式1
        Optional<Integer> sum = list8.stream().reduce((a, b) -> a + b);
        System.out.println("list求和方式1：" + sum.get());
        // 求和方式2
        Optional<Integer> sum2 = list8.stream().reduce(Integer::sum);
        System.out.println("list求和方式2：" + sum2.get());
        // 求和方式3
        Integer sum3 = list8.stream().reduce(0, Integer::sum);
        System.out.println("list求和方式3：" + sum3);
        // 求乘积
        Optional<Integer> product = list8.stream().reduce((x, y) -> x * y);
        System.out.println("list求积：" + product.get());
        // 求最大值方式1 三元表达式
        Optional<Integer> max6 = list8.stream().reduce((x, y) -> x > y ? x : y);
        System.out.println("list最大值写法1：" + max6.get());
        // 求最大值写法2
        Integer max8 = list8.stream().reduce(1, Integer::max);
        System.out.println("list最大值写法2：" + max8);

//        -------------------------------------------------------------------------------------------------
        //求所有员工的工资之和和最高工资。
        // 求工资之和方式1：
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("工资求和方式1：" + sumSalary.get());
        // 求工资之和方式2：
        Integer sumSalary2 = personList.stream().reduce(0, (sum9, p) -> sum9 += p.getSalary(),
                (sum1, sum10) -> sum1 + sum10);
        System.out.println("工资求和方式2：" + sumSalary2);

       // 求工资之和方式3：
        Optional<Person> optional = personList.stream().max(Comparator.comparing(Person::getSalary));
        System.out.println("求工资之和 - max "  + optional.get());

        Integer sumSalary3 = personList.stream().reduce(0, (sum11, p) -> sum11 += p.getSalary(), Integer::sum);
        System.out.println("工资求和方式3 - reduce ：" + sumSalary3);


        // 求最高工资方式1：
//        Integer maxSalary = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
//                Integer::max);
//        // 求最高工资方式2：
//        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
//                (max1, max2) -> max1 > max2 ? max1 : max2);
//
//        System.out.println("最高工资：" + maxSalary + "," + maxSalary2);



//        -------------------------------------------------------------------------------------------------
//        -------------------------------------------------------------------------------------------------
//        -------------------------------------------------------------------------------------------------
//        -------------------------------------------------------------------------------------------------
//        -------------------------------------------------------------------------------------------------


//        List<String> fiterList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName)
//                .collect(Collectors.toList());
//        System.out.print("高于8000的员工姓名：" + fiterList);


//
//        List<String> list = Arrays.asList("a", "b", "c");
//
//        // 创建一个顺序流, 单线程执行任务
//        Stream<String> stream1 = list.stream();
//
//        // 创建一个并行流, 多线程执行任务  参考地址:https://www.cnblogs.com/huanghuanghui/p/10211889.html
//        Stream<String> parallelStream = list.parallelStream();
//        System.out.println(Thread.currentThread().getName());
//
//        //使用java.util.Arrays.stream(T[] array)方法用数组创建流
//        int[] array={1,3,5,6,8};
//        IntStream stream2 = Arrays.stream(array);
//
//        // 使用Stream的静态方法：of()、iterate()、generate()
//        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
//
//        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
//        stream3.forEach(System.out::println);
//
//        Stream<Double> stream4 = Stream.generate(Math::random).limit(3);
//        stream4.forEach(System.out::println);
//

    }
}

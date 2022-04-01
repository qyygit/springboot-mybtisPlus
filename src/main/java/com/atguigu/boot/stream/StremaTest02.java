package com.atguigu.boot.stream;

import com.atguigu.boot.bean.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/17 10:31
 **/
@Slf4j
public class StremaTest02 {

        public static void main(String[] args) {

            //Stream 可以通过集合和数组创建
            // 1 通过 java.util.Collection.stream() 方法用集合创建流
            List<String> list = Arrays.asList("a", "b", "c");

            // 创建顺序流
            Stream<String> stream = list.stream();

            // 创建并行流
            Stream<String> parallelStream = list.parallelStream();

            // 2 使用数组创建流
            int[] array = {1,3,5,6,8};
            IntStream stream1 = Arrays.stream(array);

            // 3 Stream 的静态方法 of(), iterate() , geberate()
            Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);

            Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 3).limit(4);
            stream3.forEach(System.out::println);

            Stream<Double> stream4 = Stream.generate(Math::random).limit(1);
            stream4.forEach(System.out::println);

            List<Person> personList = new ArrayList<Person>();
            personList.add(new Person("Tom", 8900, 23, "male", "New York"));
            personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
            personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
            personList.add(new Person("Anni", 8200, 24, "female", "New York"));
            personList.add(new Person("Owen", 9500, 25, "male", "New York"));
            personList.add(new Person("Alisa", 7900, 26, "female", "New York"));


            // 3 Stream的使用
            // Optional类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
            //更详细说明请见：菜鸟教程Java 8 Optional类   地址 : https://www.runoob.com/java/java8-optional-class.html


            // 遍历/匹配（foreach/find/match）
            List<Integer> integerList = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

            // 过滤集合 元素大于 5 的
            integerList.stream().filter(p->p>5).forEach(System.out::println);

            // 过滤元素 , 获取第一个匹配元素
            Optional<Integer> integerOptional = integerList.stream().filter(x -> x > 6).findFirst();
            Integer integer = integerOptional.get();
            System.out.println("获取第一个 大于 6 的元素 : " + integer);

            //过滤元素, 获取任意满足条件元素 , 适用于 并行流
            Optional<Integer> any = null;
            try {
                boolean present = integerList.parallelStream().filter(p -> p > 8).findAny().isPresent();

                System.out.println("是否存在大于8的元素 : " + present);
                if (present){
                    Optional<Integer> optional = integerList.parallelStream().filter(x -> x > 8).findAny();
                    System.out.println("大于 8 的元素,值为 : " + optional.get());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean anyMatch = integerList.stream().anyMatch(p->p >6);
            System.out.println("是否存在大于6的值：" + anyMatch);

            // 集合过滤,匹配路径   遍历/匹配
            List<String> stringList = Arrays.asList("abc", "bcd", "哈哈哈哈哈哈哈哈哈哈啊啊", "","");
            boolean anyMatch2 = stringList.stream().anyMatch(p->p.contains("bs"));
            System.out.println("是否存在指定元素值：" + anyMatch2);

            // 筛选 filter
            List<Integer> list2 = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
            List<Integer> collect = list2.stream().filter(X -> X > 6).collect(Collectors.toList());
            System.out.println("根据条件过滤, 获取新的集合 : " + collect);


            //  筛选员工中工资高于8000的人，并形成新的集合。 形成新集合依赖collect（收集）
            List<String> collect1 = personList.stream().filter(p -> p.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
            System.out.println("工资大于8000的员工信息名称: " + collect1);


            // 聚合（max/min/count)
            Integer integer1 = list2.stream().max(Integer::max).get();
            System.out.println("Integer 集合,获取做大值 : " + integer1);

            String s = stringList.stream().max(Comparator.comparing(String::length)).get();
            System.out.println("String 集合,获取做大长度 : " + s);

            //获取 Integer 集合最大值, 自然排序,自定义排序
            Integer integer2 = list2.stream().max(Integer::compareTo).get();
            System.out.println("Integer 集合最大值, 自然排序," + integer2);


            // 自定义排序
            Integer integer3 = list2.stream().max(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            }).get();
            System.out.println("自定义排序,最大值 : " + integer3);


            // 获取员工工资最高的人。
            String maxName = personList.stream().max(Comparator.comparing(Person::getSalary)).map(Person::getName).get();
            System.out.println("工资最高的员工姓名 : " +  maxName);


            // 获取集合中大于 6 的统计数量
            long count = list2.stream().filter(p -> p > 6).count();
            System.out.println("集合中大于6的数量 : " + count);



            // 映射 map/flatMap

            String[] arr = {"asdsad","dasdsaddsa","dasdsadas"};
            String arrStr = "dasdas";
            String s1 = arrStr.toUpperCase();
            System.out.println("字符串转成大写 : " + s1);
            List<String> collect2 = Arrays.stream(arr).map(String::toUpperCase).collect(Collectors.toList());
            System.out.println("将元素小写转换成大写集合 : " + collect2);


            List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
            List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());
            System.out.println("每个元素+3：" + intListNew);

            // 案例二：将员工的薪资全部增加1000。
            List<Integer> collect3 = personList.stream().map(p -> p.getSalary() + 1000).collect(Collectors.toList());
            System.out.println("在原来基础上,所有员工工资加1000 : " +collect3);






            List<Integer> integerList1 = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
            List<Integer> listNew = integerList1.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
            Set<Integer> set = integerList1.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

            List<Person> personList3 = new ArrayList<Person>();
            personList3.add(new Person("Tom", 8900, 23, "male", "New York"));
            personList3.add(new Person("Jack", 7000, 25, "male", "Washington"));
            personList3.add(new Person("Lily", 7800, 21, "female", "Washington"));
            personList3.add(new Person("Anni", 8200, 24, "female", "New York"));

            Map<?, Person> map = personList3.stream().filter(p -> p.getSalary() > 8000)
                    .collect(Collectors.toMap(Person::getName, p -> p));
            System.out.println("toList:" + listNew);
            System.out.println("toSet:" + set);
            System.out.println("toMap:" + map);

            // 统计(count/averaging)

            List<Person> personList4 = new ArrayList<Person>();
            personList4.add(new Person("Tom", 8900, 23, "male", "New York"));
            personList4.add(new Person("Jack", 7000, 25, "male", "Washington"));
            personList4.add(new Person("Lily", 7800, 21, "female", "Washington"));

            //统计员工数量
            Long collect4 = personList4.stream().collect(Collectors.counting());
            Long collect5 = personList4.stream().count();
            System.out.println("统计员工数量 第一种方式 : " + collect4);
            System.out.println("统计员工数量 第二种方式 : " + collect5);

            //统计员工平均工资
            Double collect6 = personList4.stream().collect(Collectors.averagingDouble(Person::getSalary));
            System.out.println("统计员工平均工资 : " + collect6);

            //统计员工最高工资
            Integer integer4 = personList4.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compareTo)).get();
            System.out.println("统计员工最高工资 : " + integer4);

            //统计员工工资之和
            Integer collect7 = personList4.stream().collect(Collectors.summingInt(Person::getSalary));
            System.out.println("统计员工工资之和 : " + collect7);

            // 全部信息 数量 ,和 , 最小值, 平均值, 最大值
            DoubleSummaryStatistics collect8 = personList4.stream().collect(Collectors.summarizingDouble(Person::getSalary));
            System.out.println(" 全部信息 数量 ,和 , 最小值, 平均值, 最大值 : " + collect8);


            //Integer  集合获取   数量 ,和 , 最小值, 平均值, 最大值
            Long collect9 = integerList1.stream().collect(Collectors.counting());
            System.out.println(collect9);

            // 3.6.3 分组(partitioningBy/groupingBy)

            // 将员工按薪资是否高于8000分组
            Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
            // 将员工按性别分组
            Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
            // 将员工先按性别分组，再按地区分组
            Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
            System.out.println("员工按薪资是否大于8000分组情况：" + part);
            System.out.println("员工按性别分组情况：" + group);
            System.out.println("员工按性别、地区：" + group2);


            //joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
            String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
            System.out.println("所有员工的姓名：" + names);
            List<String> list4 = Arrays.asList("A", "B", "C");
            String string = list4.stream().collect(Collectors.joining("-"));
            System.out.println("拼接后的字符串：" + string);





            class Person {
                private String name;  // 姓名
                private int salary; // 薪资
                private int age; // 年龄
                private String sex; //性别
                private String area;  // 地区

                // 构造方法
                public Person(String name, int salary, int age,String sex,String area) {
                    this.name = name;
                    this.salary = salary;
                    this.age = age;
                    this.sex = sex;
                    this.area = area;
                }
                // 省略了get和set，请自行添加

            }



        }






}

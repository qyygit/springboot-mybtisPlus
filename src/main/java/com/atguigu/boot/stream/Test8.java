package com.atguigu.boot.stream;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/13 10:55
 **/
public class Test8 {

  public   String print(int i,String str)
    {
        System.out.println("1111111");
        return str;
    }

  public   String print(String str,int i)
    {
        System.out.println("222222222222222");
        return str;
    }
    public static void main(String[] args) {
//        Optional<Integer> optionalInteger = Stream.iterate(1, x -> x+1).limit(200).peek(x->{
//                    System.out.println(Thread.currentThread().getName());
//                }
//        ).max(Integer::compareTo);//输出 main main Optional[200]，始终使用的是主线程，说明流默认是顺序流，使用的是主线程
//        System.out.println(optionalInteger);
        Integer a = 8;
        switch (a){
            case 1 :
                System.out.println("我是1");
            case 2 :
                System.out.println("我是2");
            case 8 :
                System.out.println("我是8");
        }
        Test8 test8 = new Test8();
        test8.print(1,"11111111");
        test8.print("11111111111111",1);


//        int[] arr = {1,2};
//        System.out.println(Arrays.stream(arr).filter());
//        int arr2[];


//        Optional<Integer> optional = Stream.iterate(1, x -> x+1).limit(200).peek(x->{
//                    System.out.println(Thread.currentThread().getName());
//                }
//        ).parallel().max(Integer::compareTo);//加上 .parallel()可以将其修改成并行流，内部以多线程并行执行任务的方式执行
//        //输出：说明有多个线程在并行执行
//        //ForkJoinPool.commonPool-worker-2
//        //ForkJoinPool.commonPool-worker-3
//        //Optional[200]
//        System.out.println(optional);
//
//
//        //将并行流变成顺序流 加上.sequential()
//        Optional<Integer> optional1 = Stream.iterate(1, x -> x+1).limit(200).peek(x->{
//                    System.out.println(Thread.currentThread().getName());
//                }
//        ).parallel().sequential().max(Integer::compareTo);
//        //设置lambda表达式并行的线程数量，使用parallelism
//        //设置启动变量:加上这个参数设置 java.util.concurrent.ForkJoinPool.common.parallelism
//        //设置为5个线程数量
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","5");
    }
}

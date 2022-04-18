package com.atguigu.boot.thread.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutors {
    private static final int processorNumber = Runtime.getRuntime().availableProcessors();

    private static class ThreadPoolExecutorsHolder {
        private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(processorNumber);
    }

    private ThreadPoolExecutors() {
    }

    public static ExecutorService getSingletonExecutor() {
        System.out.println("处理器数量：" + processorNumber);
        return ThreadPoolExecutorsHolder.EXECUTOR;
    }


}
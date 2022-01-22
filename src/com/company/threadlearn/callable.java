package com.company.threadlearn;

import java.util.concurrent.*;

public class callable {


    public static void doCall() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call");
                TimeUnit.SECONDS.sleep(10);
                return "fuck";
            }
        });
        System.out.println("before get data.");
        //get 是个block的方法
        System.out.println(future.get());
    }


    public static void doCall2() throws Exception {

        ExecutorService executor=Executors.newCachedThreadPool();

        Future<String> future=executor.submit(new loadText<>());

        System.out.println(future.get());

    }

    public void newThread(){


    }
}

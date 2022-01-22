package com.company.threadlearn;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class loadText<String> implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("start line of call");
        TimeUnit.SECONDS.sleep(10);
        return (String) "fuck";
    }
}

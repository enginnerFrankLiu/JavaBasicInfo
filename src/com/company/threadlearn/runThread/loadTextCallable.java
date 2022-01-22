package com.company.threadlearn.runThread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class loadTextCallable<String> implements Callable<String> {

    @Override
    public String call()  throws Exception{
        TimeUnit.SECONDS.sleep(5);
        return (String) "some info";
    }
}

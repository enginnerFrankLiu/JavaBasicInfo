package com.company;

import java.util.concurrent.Callable;

public class IOThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        int count=0;
        for(int i=100;i<120;i++){
            Thread.sleep(2000L);
            count+=i;
        }
        return String.valueOf(count);
    }
}

package com.company.threadlearn.runThread;

import java.util.concurrent.TimeUnit;

public class printRunnable implements Runnable {

    private volatile boolean isCancel;

    /**
     * 这种停止方式依然不是靠谱的
     * 因为 call cancel的时候，
     * 可能 还会执行一次
     * 因为在下次的检查中；
     * 并没有立即执行停止；
     */
    @Override
    public void run() {
        while (!isCancel) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("print information.");
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
        System.out.println("print task already stop.");
    }

    public void cancel() {
        isCancel = true;
    }
}

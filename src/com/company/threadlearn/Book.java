package com.company.threadlearn;

public class Book {


    private int header;
    private int body;

    /**
     * 还是先肤浅的了解，真个概念，我们后续的学习，再进行各种深入的了解....
     *
     * 这连个方法是可以分别被不同的线程，同时并发的执行的；
     * 然后一个线程会block 另外一个线程的的圆形
     *
     * 盲目的去stop 一个线程，整个程序会被迫停止，并且抛出ThreadDeath 异常，可能会导致一些清理工作无法完成
     * 如：文件，数据库，网络等操作。
     *
     * 2.会理解释放掉线程持有的所有锁，导致数据处理等不到同步，出现数据不一致的情况，
     *
     * 3.一个线程长时间（永久）无法释放资源，另一个线程长时间（永久）无法获得资源；
     * 导致“长时间（永久的）” block ，不并与愿意把这里case 归结于死锁；
     *
     * suspend方法为例，该方法容易导致死锁，因为该线程在暂停的时候仍然占有该资源，这会导致其他需要该资源的线程与该线程产生环路等待，从而造成死锁
     *
     * stop方法同样如此，该方法在终结一个线程时不会保证线程的资源正常释放，通常是没有给予线程完成资源释放工作的机会，因此会导致程序在可能在工作状态不确定的情况下工作
     *
     *
     * 当我们要终止某个任务的时候，我们不应是直接去kill 掉某个线程，这个做法不是，特别好
     * 1.线程持有的资源是否能够被正确的释放掉
     * 2.会不会有其他的副作用
     * 3.是否会导致数据的不止行为的产生
     *
     * 正确的做法是，判断的它的标识;
     *
     * 不是去终止线程本事，
     * 而是去终止线程在执行的任务！！！！！！
     *
     * try{
     * if(someThread.isInterrupt()){
     *
     * //如果线程未处于睡眠或等待状态，则调用interrupt()方法会将interrupted标志设置为true，
     * 以后Java程序员可以使用该标志来停止线程在执行的任务；
     *
     * }
     * }catch(exception exception){
     *
     * 在终止线程之后，我们将处理异常，因此它将打破睡眠状态，但不会停止工作。
     *
     * }finally{
     *   //资源的释放
     * }
     *
     * https://blog.csdn.net/loongshawn/article/details/53034176
     *
     */
    public void WriteHeader() {

        synchronized (this) {
            try {
                System.out.println("A start....");
                Thread.sleep(8000L);
                header++;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("B end....");
        }
    }

    void WriteBody() {
        synchronized (this) {
            try {
                System.out.println("B start ...");
                body++;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("B end ...");
        }
    }

}

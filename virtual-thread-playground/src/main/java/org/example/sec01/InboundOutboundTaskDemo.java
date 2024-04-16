package org.example.sec01;

import java.util.concurrent.CountDownLatch;

// Create Platform thread
public class InboundOutboundTaskDemo {
    private static final int MAX_PLATFORM = 10;
    private static final int MAX_VIRTUAL = 10;


    public static void main(String[] args) throws InterruptedException {
       // platformThreadDemo5();
        virtualThreadDemo3();
    }


    /*
        To create a simple java platform thread
     */
    private static void platformThreadDemo1(){

        for(int i = 0; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = new Thread(() -> Task.isIntensive(j));
            thread.start();
        }
    }

    private static void platformThreadDemo2(){

        for(int i = 0; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = Thread.ofPlatform().unstarted(() -> Task.isIntensive(j));
            thread.start();
        }
    }


    /*
       To create platform thread using Thread.Builder
   */
    private static void platformThreadDemo3(){
        //Thread.Builder.OfPlatform builder = Thread.ofPlatform().name("vins", 1);
        var builder = Thread.ofPlatform().name("vins", 1);
        for(int i = 0; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> Task.isIntensive(j));
            thread.start();
        }
    }

/*
    Here creating a Daemon thread
    to run in the background mode
    main thread not wait for Daemon thread
    For example : Java Garbed Collector
 */
    private static void platformThreadDemo4(){
        var builder = Thread.ofPlatform().daemon().name("daemon", 1);
        for(int i = 0; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> Task.isIntensive(j));
            thread.start();
        }
    }

    /*
    Here creating a CountDown latch thread
    to run in the background mode
    main thread  wait for Daemon thread
 */
    private static void platformThreadDemo5() throws InterruptedException {
        var latch =  new CountDownLatch(MAX_PLATFORM);
        var builder = Thread.ofPlatform().daemon().name("daemon", 1);
        for(int i = 0; i < MAX_PLATFORM; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.isIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }


    //--------------------------Virtual Thread----------------------//

    /*
        To Create virtual thread using Thread.Builder
        - virtual threads are daemon by default
        - We cannot create a non daemon virtual thread
        - virtual threads don't have any default name
     */
    private static void virtualThreadDemo1(){
        var builder = Thread.ofVirtual();
        for(int i = 0; i < MAX_VIRTUAL; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> Task.isIntensive(j));
            thread.start();
        }
    }

    /*
         Here creating a CountDown
    */
    private static void virtualThreadDemo2() throws InterruptedException {
        var latch = new CountDownLatch(MAX_VIRTUAL);
        var builder = Thread.ofVirtual();
        for(int i = 0; i < MAX_VIRTUAL; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.isIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }

    /*
         Here creating a CountDown with name
    */
    private static void virtualThreadDemo3() throws InterruptedException {
        var latch = new CountDownLatch(MAX_VIRTUAL);
        var builder = Thread.ofVirtual().name("virtual-", 1);
        for(int i = 0; i < MAX_VIRTUAL; i++){
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.isIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }
}

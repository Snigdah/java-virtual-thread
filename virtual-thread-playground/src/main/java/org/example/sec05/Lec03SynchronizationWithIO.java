package org.example.sec05;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Lec03SynchronizationWithIO {
    private static final Logger log = LoggerFactory.getLogger(Lec03SynchronizationWithIO.class);
    private static final List<Integer> list = new ArrayList<>();

    // Check Pinned thread issue
//    static {
//        System.setProperty("jdk.tracePinnedThreads", "full");
//    }

    public static void main(String[] args) {

        Runnable runnable = () -> log.info("*** Test Message ***");

        demo(Thread.ofVirtual());
        Thread.ofVirtual().start(runnable);

//        demo(Thread.ofPlatform());
//        Thread.ofPlatform().start(runnable);

        CommonUtils.sleep(Duration.ofSeconds(15));


    }

    private static void demo(Thread.Builder builder){
        for (int i = 0; i < 50; i++ ){
            builder.start(() -> {
                log.info("Task started. {}", Thread.currentThread());
                ioTask();
                log.info("Task ended. {}", Thread.currentThread());
            });
        }
    }

    // Remove synchronized when run VT
    private static synchronized void ioTask(){
        list.add(1);
        CommonUtils.sleep(Duration.ofSeconds(10));
    }
}

package org.example.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void isIntensive(int i){
        try {
            log.info("staring I/O task {}. Thread Info : {}", i, Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(10));
            log.info("ending I/O task {}. Thread Info : {}", i, Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

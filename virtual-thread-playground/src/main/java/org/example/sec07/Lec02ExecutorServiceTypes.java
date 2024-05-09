package org.example.sec07;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lec02ExecutorServiceTypes {

    private static final Logger log = LoggerFactory.getLogger(Lec01AutoCloseable.class);

    public static void main(String[] args) {

    }

    private static void execute(ExecutorService executorService, int taskCount){
       try(executorService){
           for (int i = 0; i < taskCount; i++){
               int j = i;
               executorService.submit(() -> ioTask(j));
           }
       }
    }

    private  static void ioTask(int i){
        log.info("Task started: {}. Thread info {}", i, Thread.currentThread());
        CommonUtils.sleep(Duration.ofSeconds(5));
        log.info("Task ended: {}. Thread info {}", i, Thread.currentThread());
    }
}

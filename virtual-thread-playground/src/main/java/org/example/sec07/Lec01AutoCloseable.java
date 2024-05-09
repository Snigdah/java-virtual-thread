package org.example.sec07;

import org.example.sec06.Lec01ThreadFactory;
import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*
    ExecutorService now extends the AutoCloseable from JAVA 21
*/
public class Lec01AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(Lec01AutoCloseable.class);

    public static void main(String[] args) {

//       var executorService =  Executors.newSingleThreadExecutor();
//       executorService.submit(() -> task());
//       log.info("submitted");
//       executorService.shutdown();
       //executorService.shutdownNow();
       //executorService.submit(() -> task());


        try (var executorService =  Executors.newSingleThreadExecutor();){
            executorService.submit(() -> task());
            executorService.submit(() -> task());
            executorService.submit(() -> task());
            executorService.submit(() -> task());
            log.info("submitted");
        }

    }


    private static void task (){
        CommonUtils.sleep(Duration.ofSeconds(1));
        log.info("Task executed");
    }
}

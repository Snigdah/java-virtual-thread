package org.example.sec3;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CPUTaskDemo {
    private static final Logger log = LoggerFactory.getLogger(org.example.sec01.Task.class);
     private static final int TASK_COUNT = 5;
   //  private static final int TASK_COUNT = 3 * Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) {
//        System.out.println(
//                //Task.findFib(80)
//               //CommonUtils.timer(() -> Task.findFib(48))
//        );


        log.info("Task count: {}", TASK_COUNT);
        for (int i = 1; i <= 3; i++){
            var totallTimeTaken = CommonUtils.timer(() -> demo(Thread.ofVirtual()));
            log.info("Total time taken withg virtual {} ms", totallTimeTaken);

            totallTimeTaken = CommonUtils.timer(() -> demo(Thread.ofPlatform()));
            log.info("Total time taken withg Platform {} ms", totallTimeTaken);
        }

       // demo(Thread.ofPlatform());
      //   demo(Thread.ofVirtual());

    }

    private static void demo(Thread.Builder builder){
        var latch = new CountDownLatch((TASK_COUNT));
        for (int i = 1; i <= TASK_COUNT; i++){
            builder.start(() -> {
                Task.cpuIntensive(45);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

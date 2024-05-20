package org.example.sec07;

import org.example.sec07.externalService.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lec05ConcurrencyLimit {
    private static final Logger log = LoggerFactory.getLogger(Lec05ConcurrencyLimit.class);

    public static void main(String[] args) {
        var factory = Thread.ofVirtual().name("vins", 1).factory();
        execute(Executors.newFixedThreadPool(3, factory), 20);
       // execute(Executors.newFixedThreadPool(3), 20);
    }

    private static void execute(ExecutorService executorService, int taskCount){
        try(executorService){
            for(int i = 1; i <= taskCount; i++){
                int j =i;
                executorService.submit(() -> printProductInfo(j));
            }
            log.info("submitted");
        }
    }

    // 3rd party service
    // contract: 3 concurrent calls are allowed
    private static void printProductInfo(int id){
        log.info("{} => {}", id, Client.getProduct(id));
    }
}

package org.example.sec08;

import org.example.sec07.Lec01AutoCloseable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class Lec015SimpleCompletableFuture {
    private static final Logger log = LoggerFactory.getLogger(Lec015SimpleCompletableFuture.class);

    public static void main(String[] args) {
        log.info("main starts");

        var cf = fastTask();
        log.info("value={}", cf.join());

        log.info("main ends");
    }

    private static CompletableFuture<String> fastTask(){
        log.info("method starts");
        var cf = new CompletableFuture<String>();
        cf.complete("hi");
        log.info("method ends");
        return cf;
    }


}

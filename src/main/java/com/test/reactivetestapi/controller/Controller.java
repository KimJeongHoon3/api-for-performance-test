package com.test.reactivetestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class Controller {
    ExecutorService executorService;

    public Controller() {
         executorService= Executors.newFixedThreadPool(1000);
    }

    @GetMapping("/test/api")
    Mono<String> testApi() {
        return Mono.fromSupplier(()->{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "complete";
                }).subscribeOn(Schedulers.fromExecutor(executorService));
    }
}

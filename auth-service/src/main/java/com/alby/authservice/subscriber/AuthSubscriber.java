package com.alby.authservice.subscriber;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class AuthSubscriber {
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    public void subscribeMessage(String message) {
        System.out.println("Received : " + message);
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}

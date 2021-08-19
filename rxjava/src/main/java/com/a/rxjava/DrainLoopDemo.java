package com.a.rxjava;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class DrainLoopDemo {

    public static void main(String[] args) throws InterruptedException {
        new DrainLoopDemo().produce();
        Thread.sleep(100000);
    }

    private final ConcurrentLinkedDeque<String> q = new ConcurrentLinkedDeque<>();
    private final AtomicInteger wip = new AtomicInteger();
    private int taskCount = 0;


    private void produce() {
        for (int i = 1; i <= 10; i++) {
            final int ix = i;
            new Thread(() -> {
                int millis = new Random().nextInt(5) + 1;
                q.offer("task:" + ix + " random:" + millis);
                drainLoop1();
            }, "Thread:" + ix).start();
        }
    }

    private void drainLoop1() {
        if (wip.getAndIncrement() != 0) return;
        int delta = wip.get();
        do {
            String tag;
            while ((tag = q.poll()) != null) {
                taskCount++;
                System.out.println("execute  " + tag + " taskCount:" + taskCount + " " + Thread.currentThread().getName());
            }
            delta = wip.addAndGet(-delta);
        } while (delta != 0);
    }

    private void drainLoop2() {
        String tag = q.poll();
        if (tag == null) return;
        taskCount++;
        System.out.println("execute  " + tag + " taskCount:" + taskCount + " " + Thread.currentThread().getName());
    }
}

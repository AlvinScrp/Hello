package com.a.rxjava;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class DrainLoopDemo2 {

    public static void main(String[] args) throws InterruptedException {
        new DrainLoopDemo2().produce();
        Thread.sleep(100000);
    }

    private final AtomicInteger wip = new AtomicInteger();
    private int taskCount = 0;
    private final int Task_Max = 20;

    private long start;

    private void produce() {
        start = System.currentTimeMillis();
        for (int i = 1; i <= Task_Max; i++) {
            new Thread(() -> drainLoop2(), "Thread:" + i).start();
        }
    }

    private void drainLoop1() {
//        int millis = new Random().nextInt(5) + 1;
        if (wip.getAndIncrement() != 0) return;
        int delta = wip.get();
        do {
            for (int i = 0; i < delta; i++) {
                taskCount++;
                System.out.println("execute  " + " taskCount:" + taskCount + " " + Thread.currentThread().getName());
                if (taskCount == Task_Max) costTime();
            }
            delta = wip.addAndGet(-delta);
        } while (delta != 0);
    }

    private  void drainLoop2() {
        taskCount++;
        System.out.println("execute  " + " taskCount:" + taskCount + " " + Thread.currentThread().getName());
        if (taskCount == Task_Max) costTime();
    }

    private void costTime() {
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }
}

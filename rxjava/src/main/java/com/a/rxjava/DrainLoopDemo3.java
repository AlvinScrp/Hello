package com.a.rxjava;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.internal.queue.MpscLinkedQueue;

public class DrainLoopDemo3 {

    public static void main(String[] args) throws InterruptedException {
        new DrainLoopDemo3().produce();
        Thread.sleep(100000);
    }

    private final MpscLinkedQueue<String> q = new MpscLinkedQueue<>();
    private final AtomicInteger wip = new AtomicInteger();
    private int taskCount = 0;


    private void produce() {
        for (int i = 1; i <= 100; i++) {
            final int ix = i;
            new Thread(() -> {
                threadSleep(20);
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
                threadSleep(100);
                System.out.println("execute  " + tag + " taskCount:" + taskCount + " " + Thread.currentThread().getName());
            }
            delta = wip.addAndGet(-delta);
        } while (delta != 0);
    }
    
    private void  threadSleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    

    private void drainLoop2() {
        String tag = q.poll();
        if (tag == null) return;
        taskCount++;
        System.out.println("execute  " + tag + " taskCount:" + taskCount + " " + Thread.currentThread().getName());
    }
}

/**
 * Copyright (c) 2016-present, RxJava Contributors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package com.a.rxjava;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public final class FlowableObserveOn2<T> extends Flowable<T> implements HasUpstreamPublisher<T> {
    final Scheduler scheduler;
    final int prefetch;
    final Publisher<T> source;

    public FlowableObserveOn2(Flowable<T> source, int prefetch) {
        this.source = source;
        this.scheduler = Schedulers.newThread();
        this.prefetch = prefetch;
    }

    @Override
    public void subscribeActual(Subscriber<? super T> s) {
        Worker worker = scheduler.createWorker();
        source.subscribe(new ObserveOnSubscriber<T>(s, worker, prefetch));
    }

    @Override
    public Publisher<T> source() {
        return source;
    }

    static class ObserveOnSubscriber<T>
            extends AtomicInteger
            implements FlowableSubscriber<T>, Runnable, Subscription {

        final Subscriber<? super T> actual;

        final Worker worker;

        final int prefetch;

        final int limit;

        final AtomicLong requested;

        Subscription s;

        SimpleQueue<T> queue;

        volatile boolean cancelled;

        volatile boolean done;

        Throwable error;

        long produced;


        ObserveOnSubscriber(
                Subscriber<? super T> actual,
                Worker worker,
                int prefetch) {
            this.actual = actual;
            this.worker = worker;
            this.prefetch = prefetch;
            this.requested = new AtomicLong();
            this.limit = prefetch - (prefetch >> 2);
        }


        @Override
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.s, s)) {
                this.s = s;

                queue = new SpscArrayQueue<T>(prefetch);

                actual.onSubscribe(this);

                s.request(prefetch);
            }
        }

        @Override
        public final void onNext(T t) {
            if (done) {
                return;
            }

            if (!queue.offer(t)) {
                s.cancel();

                error = new MissingBackpressureException("Queue is full?!");
                done = true;
            }
            trySchedule();
        }

        @Override
        public final void onError(Throwable t) {
            if (done) {
                RxJavaPlugins.onError(t);
                return;
            }
            error = t;
            done = true;
            trySchedule();
        }

        @Override
        public final void onComplete() {
            if (!done) {
                done = true;
                trySchedule();
            }
        }

        @Override
        public final void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(requested, n);
                trySchedule();
            }
        }

        @Override
        public final void cancel() {
            if (cancelled) {
                return;
            }

            cancelled = true;
            s.cancel();
            worker.dispose();

            if (getAndIncrement() == 0) {
                queue.clear();
            }
        }

        final void trySchedule() {
            if (getAndIncrement() != 0) {
                return;
            }
            worker.schedule(this);
        }

        @Override
        public final void run() {
            runAsync();
        }

        void runAsync() {
            int missed = 1;

            final Subscriber<? super T> a = actual;
            final SimpleQueue<T> q = queue;

            long e = produced;

            for (; ; ) {

                long r = requested.get();
                System.out.println("e:" + e + ",r:" + r);

                while (e != r) {
                    boolean d = done;
                    T v;

                    try {
                        v = q.poll();
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);

                        s.cancel();
                        q.clear();

                        a.onError(ex);
                        worker.dispose();
                        return;
                    }

                    boolean empty = v == null;

                    if (checkTerminated(d, empty, a)) {
                        return;
                    }

                    if (empty) {
                        break;
                    }

                    a.onNext(v);

                    e++;
                    if (e == limit) {
                        if (r != Long.MAX_VALUE) {
                            r = requested.addAndGet(-e);
                        }
                        s.request(e);
                        e = 0L;
                    }
                }

                int w = get();
                System.out.println(String.format("w:%d,missed:%d", w, missed));
                if (missed == w) {
                    produced = e;
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        break;
                    }
                } else {
                    missed = w;
                }
            }
        }

        final boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a) {
            if (cancelled) {
                return true;
            }
            if (d) {
                Throwable e = error;
                if (e != null) {
                    a.onError(e);
                    worker.dispose();
                    return true;
                } else if (empty) {
                    a.onComplete();
                    worker.dispose();
                    return true;
                }
            }

            return false;
        }
    }


}

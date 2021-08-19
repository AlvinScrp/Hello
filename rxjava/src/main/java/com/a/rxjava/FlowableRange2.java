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

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;

/**
 * Emits a range of integer values.
 */
public final class FlowableRange2 extends Flowable<Integer> {
    final int start;
    final int end;

    public FlowableRange2(int start, int count) {
        this.start = start;
        this.end = start + count;
    }

    @Override
    public void subscribeActual(Subscriber<? super Integer> s) {
        s.onSubscribe(new RangeSubscription(s, start, end));

    }

    static class RangeSubscription extends AtomicLong implements Subscription {
        final int end;
        int index;
        final Subscriber<? super Integer> actual;

        RangeSubscription(Subscriber<? super Integer> actual, int index, int end) {
            this.index = index;
            this.end = end;
            this.actual = actual;
        }

        @Override
        public final void request(long n) {
            if (BackpressureHelper.add(this, n) == 0L) {
                slowPath(n);
            }
        }

        void slowPath(long r) {
            System.out.print(String.format("\n[request:%d]", r));
            long e = 0;
            int f = end;
            int i = index;
            Subscriber<? super Integer> a = actual;

            for (; ; ) {
                while (e != r && i != f) {
                    a.onNext(i);
                    e++;
                    i++;
                }
                if (i == f) return;

                r = get();
                if (e == r) {
                    index = i;
                    r = addAndGet(-e);
                    if (r == 0L) {
                        return;
                    }
                    e = 0L;
                }
            }
        }


        @Override
        public void cancel() {
        }

    }


}

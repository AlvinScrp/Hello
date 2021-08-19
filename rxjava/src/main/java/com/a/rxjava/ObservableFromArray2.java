package com.a.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.BasicQueueDisposable;

class ObservableFromArray<T> extends Observable<T> {
    final T[] array;
    public ObservableFromArray(T[] array) {
        this.array = array;
    }
    @Override
    public void subscribeActual(Observer<? super T> s) {
        FromArrayDisposable<T> d = new FromArrayDisposable<T>(s, array);
        s.onSubscribe(d);
        d.run();
    }

    static final class FromArrayDisposable<T> implements Disposable {

        final Observer<? super T> actual;
        final T[] array;
        volatile boolean disposed;

        FromArrayDisposable(Observer<? super T> actual, T[] array) {
            this.actual = actual;
            this.array = array;
        }

        void run() {
            T[] a = array;
            int n = a.length;
            for (int i = 0; i < n && !isDisposed(); i++) {
                T value = a[i];
                if (value == null) {
                    actual.onError(new NullPointerException("The " + i + "th element is null"));
                    return;
                }
                actual.onNext(value);
            }
            if (!isDisposed()) actual.onComplete();
        }

        @Override
        public void dispose() { disposed = true;  }

        @Override
        public boolean isDisposed() { return disposed; }
    }
}


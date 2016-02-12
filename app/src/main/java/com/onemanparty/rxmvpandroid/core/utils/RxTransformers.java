package com.onemanparty.rxmvpandroid.core.utils;

import rx.Subscriber;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Helper class for common observable transformations
 */
public class RxTransformers {

    /**
     * Apply standard {@link Schedulers}: io for {@link Observable}, ui for {@link Subscriber}
     */
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Apply standard {@link Schedulers}: io for {@link Observable}, io for {@link Subscriber}
     */
    public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    /**
     * Run code before running observable {@link Observable} and after its termination
     * @param before code that will run onSubscribe
     * @param after code that will run afterTerminate
     * @param <T> {@link Object}
     * @return {@link Observable}
     */
    public static <T> Observable.Transformer<T, T> applyOpBeforeAndAfter(Runnable before, Runnable after) {
        return tObservable -> tObservable.doAfterTerminate(after::run).doOnSubscribe(before::run);
    }

}

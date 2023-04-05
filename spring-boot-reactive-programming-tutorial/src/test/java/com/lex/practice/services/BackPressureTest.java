package com.lex.practice.services;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

/**
 * @author : LEX_YU
 * @date : 05/04/2023
 */
public class BackPressureTest {

    @Test
    public void testBackPressure() {
        var numbers = Flux.range(1, 100).log();
//        numbers.subscribe(integer -> System.out.println("Integer = " + integer));

        numbers.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("Value = " + value);
                if (value == 3) cancel();
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Completed !!");
            }
        });
    }

    @Test
    public void testBackPressureDrop() {
        var numbers = Flux.range(1, 100).log();

        numbers.onBackpressureDrop(integer -> {
                    System.out.println("Dropped Values = " + integer);
                })
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Value = " + value);
                        if (value == 3) hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed !!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }

    @Test
    public void testBackPressureBuffer() {
        var numbers = Flux.range(1, 100).log();

        numbers.onBackpressureBuffer(10, i -> {
                    System.out.println("Buffered Values = " + i);
                })
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Value = " + value);
                        if (value == 3) hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed !!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }

    @Test
    public void testBackPressureError() {
        var numbers = Flux.range(1, 100).log();

        numbers.onBackpressureError()
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Value = " + value);
                        if (value == 3) hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed !!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        System.out.println("Throwable = " + throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }
}

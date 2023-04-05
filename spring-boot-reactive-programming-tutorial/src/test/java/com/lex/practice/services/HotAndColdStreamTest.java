package com.lex.practice.services;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : LEX_YU
 * @date : 05/04/2023
 */
public class HotAndColdStreamTest {

    @Test
    public void coldStreamTest() {
        var number = Flux.range(1, 10).log();

        number.subscribe(i -> System.out.println("Subscriber-1 = " + i));
        number.subscribe(i -> System.out.println("Subscriber-2 = " + i));
    }

    @Test
    public void hotStreamTest() throws InterruptedException {
        var number = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(1000))
                .log();

        ConnectableFlux<Integer> publisher = number.publish();
        publisher.connect();

        publisher.subscribe(i -> System.out.println("Subscriber-1 = " + i));
        Thread.sleep(4000);
        publisher.subscribe(i -> System.out.println("Subscriber-2 = " + i));
        Thread.sleep(10000);
    }
}

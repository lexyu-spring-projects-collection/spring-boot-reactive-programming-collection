package com.lex.cold_publisher;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * @author : Lex Yu
 * @date : 2023/4/15
 * @last_modified : 2023/4/15
 */
public class ColdStream {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> flux = Flux
                .fromStream(ColdStream::getVideo)
                .delayElements(Duration.ofSeconds(2));

        // First Subscriber
        flux.subscribe(part -> System.out.println("Subscriber 1: " + part));

        Thread.sleep(5000);

        // Second Subscriber
        flux.subscribe(part -> System.out.println("Subscriber 2: " + part));

        Thread.sleep(15000);
    }


    private static Stream<String> getVideo() {
        System.out.println(Thread.currentThread().getName() + " , request for the video streaming received.");
        return Stream.of("part 1", "part 2", "part 3", "part 4", "part 5");
    }
}

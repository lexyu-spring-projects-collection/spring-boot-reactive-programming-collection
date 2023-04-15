package com.lex.hot_publisher;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * @author : Lex Yu
 * @date : 2023/4/15
 * @last_modified : 2023/4/15
 */
public class HotStreamCache {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> flux = Flux.fromStream(HotStreamCache::getVideo)
                .delayElements(Duration.ofSeconds(2))
                .cache();

        // First Subscriber
        flux.subscribe(part -> System.out.println("Subscriber 1: " + part));

        Thread.sleep(15000);

        // Second Subscriber
        flux.subscribe(part -> System.out.println("Subscriber 2: " + part));

        Thread.sleep(15000);
    }

    private static Stream<String> getVideo() {
        System.out.println("Request for the video streaming received.");
        return Stream.of("part 1", "part 2", "part 3", "part 4", "part 5");
    }
}

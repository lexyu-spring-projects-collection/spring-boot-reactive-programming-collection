package com.lex.hot_publisher;

import com.lex.cold_publisher.ColdStream;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * @author : Lex Yu
 * @date : 2023/4/15
 * @last_modified : 2023/4/15
 */
public class HotStream {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> flux = Flux.fromStream(HotStream::getVideo)
                .delayElements(Duration.ofSeconds(2))
                .share(); // turn the cold publisher into a hot publisher

        // First Subscriber
        flux.subscribe(part -> System.out.println("Subscriber 1: " + part));

        Thread.sleep(5000);

        // Second Subscriber
        flux.subscribe(part -> System.out.println("Subscriber 2: " + part));

        Thread.sleep(15000);
    }

    private static Stream<String> getVideo() {
        System.out.println("Request for the video streaming received.");
        return Stream.of("part 1", "part 2", "part 3", "part 4", "part 5");
    }
}

package com.lex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author : Lex Yu
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public Mono<String> greetingMessage() {
//        return computeMessage();

        return computeMessage().zipWith(getNameFromDB())
                .map(values -> {
                    return values.getT1() + values.getT2();
                });
    }

    private Mono<String> computeMessage() {
        return Mono.just("Hello World").delayElement(Duration.ofSeconds(3));
    }

    private Mono<String> getNameFromDB() {
        return Mono.just("LLLLLLLL").delayElement(Duration.ofSeconds(5));
    }
}

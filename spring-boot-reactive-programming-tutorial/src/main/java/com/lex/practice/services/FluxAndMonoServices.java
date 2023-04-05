package com.lex.practice.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * @author : LEX_YU
 * @date : 2023/4/3
 */
public class FluxAndMonoServices {

    public Flux<String> fruitsFlux() {
        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .log();
    }

    public Flux<String> fruitsFluxEmptyData() {
        return Flux
                .fromIterable(List.of("Mango", "", "Banana"))
                .map(fruit -> {
                    if (fruit.isEmpty()) {
                        throw new IllegalStateException("Exception Occurred");
                    }
                    return fruit.toLowerCase();
                }).onErrorContinue((e, f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                })
                .log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int num) {
        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(fruit -> fruit.length() > num)
                .log();
    }

    public Flux<String> fruitsFluxFilterMap(int num) {
        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(fruit -> fruit.length() > num)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(fruit -> Flux.just(fruit.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(fruit -> Flux.just(fruit.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    public Flux<String> fruitsFluxConcatMap() {
        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .concatMap(fruit -> Flux.just(fruit.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }


    public Mono<String> fruitMono() {
        return Mono.just("Apple").log();
    }

    public Mono<List<String>> fruitMonoFlatMap() {
        return Mono
                .just("Apple")
                .flatMap(fruit -> Mono.just(List.of(fruit.split(""))))
                .log();
    }

    public Flux<String> fruitMonoFlatMapMany() {
        return Mono
                .just("Apple")
                .flatMapMany(fruit -> Flux.just(fruit.split("")))
                .log();
    }

    public Flux<String> fruitMonoTransform(int num) {
        Function<Flux<String>, Flux<String>> filterData
                = data -> data.filter(fruit -> fruit.length() > num);

        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData) // .filter(fruit -> fruit.length() > num)
                .log();
    }

    public Flux<String> fruitMonoTransformDefaultIfEmpty(int num) {
        Function<Flux<String>, Flux<String>> filterData
                = data -> data.filter(fruit -> fruit.length() > num);

        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData) // .filter(fruit -> fruit.length() > num)
                .defaultIfEmpty("Default")
                .log();
    }

    public Flux<String> fruitMonoTransformSwitchIfEmpty(int num) {
        Function<Flux<String>, Flux<String>> filterData
                = data -> data.filter(fruit -> fruit.length() > num);

        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData) // .filter(fruit -> fruit.length() > num)
                .switchIfEmpty(Flux.just("Pineapple", "Jack Fruit"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxConcat() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");
        return Flux.concat(fruits, veggies)
                .log();
    }

    public Flux<String> fruitsFluxConcatWith() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");
        return fruits.concatWith(veggies)
                .log();
    }

    public Flux<String> fruitsMonoConcatWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");
        return fruits.concatWith(veggies)
                .log();
    }

    public Flux<String> fruitsFluxMerge() {
        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(100));
        var veggies = Flux.just("Tomato", "Lemon")
                .delayElements(Duration.ofMillis(75));
        return Flux.merge(fruits, veggies)
                .log();
    }

    public Flux<String> fruitsFluxMergeWith() {
        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(100));
        var veggies = Flux.just("Tomato", "Lemon")
                .delayElements(Duration.ofMillis(75));
        return fruits.mergeWith(veggies)
                .log();
    }

    public Flux<String> fruitsFluxMergeSequential() {
        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(100));
        var veggies = Flux.just("Tomato", "Lemon")
                .delayElements(Duration.ofMillis(75));
        return Flux.mergeSequential(veggies, fruits)
                .log();
    }

    public Flux<String> fruitsFluxZip() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");

        return Flux.zip(fruits, veggies,
                        (first, second) -> first + second)
                .log();
    }

    public Flux<String> fruitsFluxZipWith() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");

        return fruits.zipWith(veggies,
                        (first, second) -> first + second)
                .log();
    }

    public Flux<String> fruitsFluxZipTuple() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");
        var more = Flux.just("Potato", "Beans");

        return Flux
                .zip(fruits, veggies, more)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3())
                .log();
    }

    public Mono<String> fruitsMonoZipWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");

        return fruits
                .zipWith(veggies, (first, second) -> first + second)
                .log();
    }

    public Mono<String> fruitsMonoZipTuple() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");
        var more = Mono.just("Potato");

        return Mono
                .zip(fruits, veggies, more)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3())
                .log();
    }

    public Flux<String> fruitsFluxFilterDoOn(int num) {
        return Flux
                .fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(fruit -> fruit.length() > num)
                .doOnNext(fruit -> {
                    System.out.println("fruit = " + fruit);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("subscription = " + subscription.toString());
                })
                .doOnComplete(() -> {
                    System.out.println("Complete !!");
                })
                .log();
    }

    public Flux<String> fruitsFluxOnErrorReturn() {
        return Flux
                .just("Apple", "Mongo")
                .concatWith(Flux.error(
                        new RuntimeException("Exception Occurred")
                ))
                .onErrorReturn("Orange")
                .log();
    }

    public Flux<String> fruitsFluxOnErrorContinue() {
        return Flux
                .just("Apple", "Mango", "Orange")
                .map(fruit -> {
                    if (fruit.equalsIgnoreCase("Mango")) {
                        throw new RuntimeException("Exception Occurred");
                    }
                    return fruit.toUpperCase();
                })
                .onErrorContinue((e, f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                })
                .log();
    }

    public Flux<String> fruitsFluxOnErrorMap() {
        return Flux
                .just("Apple", "Mango", "Orange")
                .checkpoint("Error Checkpoint - 1")
                .map(fruit -> {
                    if (fruit.equalsIgnoreCase("Mango")) {
                        throw new RuntimeException("Exception Occurred");
                    }
                    return fruit.toUpperCase();
                })
                .checkpoint("Error Checkpoint - 2")
                .onErrorMap(throwable -> {
                    System.out.println("throwable = " + throwable);
                    return new IllegalStateException("From Error Map");
                })
                .log();
    }

    public Flux<String> fruitsFluxDoOnError() {
        return Flux
                .just("Apple", "Mango", "Orange")
                .map(fruit -> {
                    if (fruit.equalsIgnoreCase("Mango")) {
                        throw new RuntimeException("Exception Occurred");
                    }
                    return fruit.toUpperCase();
                })
                .doOnError(throwable -> { // similar try-catch
                    System.out.println("throwable = " + throwable);
                })
                .log();
    }

    public static void main(String[] args) throws InterruptedException {
        FluxAndMonoServices services = new FluxAndMonoServices();
        services.fruitsFlux()
                .subscribe(fruit -> {
                    System.out.println("Flux fruit = " + fruit);
                });

        System.out.println("---------------------------------------------------------");

        services.fruitMono()
                .subscribe(fruit -> {
                    System.out.println("Mono fruit = " + fruit);
                });

        System.out.println("---------------------------------------------------------");

        services.fruitsFluxMap()
                .subscribe(fruit -> {
                    System.out.println("Flux fruit = " + fruit);
                });

        System.out.println("---------------------------------------------------------");

        services.fruitsFluxFilter(5)
                .subscribe(fruit -> {
                    System.out.println("Flux fruit = " + fruit);
                });

        System.out.println("---------------------------------------------------------");

        services.fruitsFluxFilterMap(5)
                .subscribe(fruit -> {
                    System.out.println("Flux fruit = " + fruit);
                });

        System.out.println("---------------------------------------------------------");

        services.fruitsFluxFlatMap()
                .subscribe(fruit -> {
                    System.out.printf("%s", fruit);
                });
        System.out.println();
        System.out.println("---------------------------------------------------------");

        services.fruitsFluxFlatMapAsync()
                .subscribe(fruit -> {
                    System.out.println("Flux fruit = " + fruit);
                });

        Thread.sleep(15000);

        System.out.println("---------------------------------------------------------");

        services.fruitMonoFlatMap()
                .subscribe(fruit -> {
                    System.out.println("Mono fruit = " + fruit);
                });

        System.out.println("---------------------------------------------------------");

        services.fruitsFluxConcatMap()
                .subscribe(fruit -> {
                    System.out.println("Flux fruit = " + fruit);
                });

        Thread.sleep(15000);

        services.fruitMonoFlatMapMany()
                .subscribe(fruit -> {
                    System.out.println("Flux fruit = " + fruit);
                });
    }
}

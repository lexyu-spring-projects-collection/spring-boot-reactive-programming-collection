package com.lex.practice.services;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Hooks;
import reactor.test.StepVerifier;
import reactor.tools.agent.ReactorDebugAgent;

/**
 * @author : LEX_YU
 * @date : 2023/4/3
 */
class FluxAndMonoServicesTest {

    FluxAndMonoServices services = new FluxAndMonoServices();

    @Test
    void fruitsFlux() {
        var fruitFlux = services.fruitsFlux();
        StepVerifier
                .create(fruitFlux)
                .expectNext("Mango", "Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxEmptyData() {
        var fruitsFluxEmptyData = services.fruitsFluxEmptyData();
        StepVerifier
                .create(fruitsFluxEmptyData)
                .expectNext("mango", "banana")
                .verifyComplete();
    }

    @Test
    void fruitMono() {
        var fruitMono = services.fruitMono();
        StepVerifier
                .create(fruitMono)
                .expectNext("Apple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitFluxMap = services.fruitsFluxMap();
        StepVerifier
                .create(fruitFluxMap)
                .expectNext("MANGO", "ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFluxFilter = services.fruitsFluxFilter(5);
        StepVerifier
                .create(fruitsFluxFilter)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        var fruitsFluxFilterMap = services.fruitsFluxFilterMap(5);
        StepVerifier
                .create(fruitsFluxFilterMap)
                .expectNext("ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var fruitsFluxFlatMap = services.fruitsFluxFlatMap();
        StepVerifier
                .create(fruitsFluxFlatMap)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var fruitsFluxFlatMap = services.fruitsFluxFlatMapAsync();
        StepVerifier
                .create(fruitsFluxFlatMap)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMap() {
        var fruitMonoFlatMap = services.fruitMonoFlatMap();
        StepVerifier
                .create(fruitMonoFlatMap)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        var fruitsFluxConcatMap = services.fruitsFluxConcatMap();
        StepVerifier
                .create(fruitsFluxConcatMap)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany() {
        var fruitMonoFlatMapMany = services.fruitMonoFlatMapMany();
        StepVerifier
                .create(fruitMonoFlatMapMany)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitMonoTransform() {
        var fruitMonoTransform = services.fruitMonoTransform(5);
        StepVerifier
                .create(fruitMonoTransform)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitMonoTransformDefaultIfEmpty() {
        var fruitMonoTransformDefaultIfEmpty = services.fruitMonoTransformDefaultIfEmpty(100);
        StepVerifier
                .create(fruitMonoTransformDefaultIfEmpty)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void fruitMonoTransformSwitchIfEmpty() {
        var fruitMonoTransformSwitchIfEmpty = services.fruitMonoTransformSwitchIfEmpty(8);
        StepVerifier
                .create(fruitMonoTransformSwitchIfEmpty)
                .expectNext("Pineapple", "Jack Fruit")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitFluxConcat = services.fruitsFluxConcat();
        StepVerifier
                .create(fruitFluxConcat)
                .expectNext("Mango", "Orange", "Tomato", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitsFluxConcatWith = services.fruitsFluxConcatWith();
        StepVerifier
                .create(fruitsFluxConcatWith)
                .expectNext("Mango", "Orange", "Tomato", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {
        var fruitsMonoConcatWith = services.fruitsMonoConcatWith();
        StepVerifier
                .create(fruitsMonoConcatWith)
                .expectNext("Mango", "Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitsFluxMerge = services.fruitsFluxMerge();
        StepVerifier
                .create(fruitsFluxMerge)
//                .expectNext("Mango", "Tomato","Orange", "Lemon")
                .expectNext("Tomato", "Mango", "Lemon", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitsFluxMergeWith = services.fruitsFluxMergeWith();
        StepVerifier
                .create(fruitsFluxMergeWith)
                .expectNext("Tomato", "Mango", "Lemon", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeSequential() {
        var fruitsFluxMergeSequential = services.fruitsFluxMergeSequential();
        StepVerifier
                .create(fruitsFluxMergeSequential)
                .expectNext("Tomato", "Lemon", "Mango", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitsFluxZip = services.fruitsFluxZip();
        StepVerifier
                .create(fruitsFluxZip)
                .expectNext("MangoTomato", "OrangeLemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitsFluxZipWith = services.fruitsFluxZipWith();
        StepVerifier
                .create(fruitsFluxZipWith)
                .expectNext("MangoTomato", "OrangeLemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitsFluxZipTuple = services.fruitsFluxZipTuple();
        StepVerifier
                .create(fruitsFluxZipTuple)
                .expectNext("MangoTomatoPotato", "OrangeLemonBeans")
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipWith() {
        var fruitsMonoZipWith = services.fruitsMonoZipWith();
        StepVerifier
                .create(fruitsMonoZipWith)
                .expectNext("MangoTomato")
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipTuple() {
        var fruitsMonoZipTuple = services.fruitsMonoZipTuple();
        StepVerifier
                .create(fruitsMonoZipTuple)
                .expectNext("MangoTomatoPotato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterDoOn() {
        var fruitsFluxFilterDoOn = services.fruitsFluxFilterDoOn(5);
        StepVerifier
                .create(fruitsFluxFilterDoOn)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturn() {
        var fruitsFluxOnErrorReturn = services.fruitsFluxOnErrorReturn();
        StepVerifier
                .create(fruitsFluxOnErrorReturn)
                .expectNext("Apple", "Mongo", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruitsFluxOnErrorContinue = services.fruitsFluxOnErrorContinue();
        StepVerifier
                .create(fruitsFluxOnErrorContinue)
                .expectNext("APPLE", "ORANGE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorMap() {
//        Hooks.onOperatorDebug();
        ReactorDebugAgent.init();
        ReactorDebugAgent.processExistingClasses();
        var fruitsFluxOnErrorMap = services.fruitsFluxOnErrorMap();
        StepVerifier
                .create(fruitsFluxOnErrorMap)
                .expectNext("APPLE")
                .expectError(IllegalStateException.class)
                .verify();
    }

    @Test
    void fruitsFluxDoOnError() {
        var fruitsFluxDoOnError = services.fruitsFluxDoOnError();
        StepVerifier
                .create(fruitsFluxDoOnError)
                .expectNext("APPLE")
                .expectError(RuntimeException.class)
                .verify();
    }
}
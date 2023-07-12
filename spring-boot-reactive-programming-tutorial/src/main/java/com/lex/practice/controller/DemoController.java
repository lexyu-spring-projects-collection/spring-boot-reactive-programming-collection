package com.lex.practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * @author : Lex Yu
 * @date : 2023/7/12
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
	@GetMapping("/get-mono")
	public ResponseEntity<?> getMono() {

		Mono<String> responseMono = Mono.just("Resource accessed successfully");

		asyncMethod();

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseMono);
	}

	private void asyncMethod() {
		CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return "This is asyncMethod after thread sleep 5 secs";
		}).thenAccept(result->{
			System.out.println("Accept Result :" + result);
		});
	}
}

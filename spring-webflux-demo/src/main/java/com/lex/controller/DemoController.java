package com.lex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author : Lex Yu
 */
@RestController
@RequestMapping
public class DemoController {

	@GetMapping("/demo")
	public Mono<String> greetingMessage(){
		return getMessage();
	}
}

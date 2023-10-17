package com.lex.controller;

import com.lex.model.Users;
import com.lex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author : Lex Yu
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Flux<Users>> findAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}

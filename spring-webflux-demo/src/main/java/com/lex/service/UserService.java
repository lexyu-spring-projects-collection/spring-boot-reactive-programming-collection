package com.lex.service;

import com.lex.model.Users;
import com.lex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Lex Yu
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public Mono<Users> getUserById(Long id) {

		return null;
	}

	public Flux<Users> getUsers() {

		return null;
	}


	public void addUser(Users users) {

	}

	public Mono<Users> updateUser(Users users) {

		return null;
	}

	public Mono<Void> deleteUser(Long id){


		return null;
	}
}

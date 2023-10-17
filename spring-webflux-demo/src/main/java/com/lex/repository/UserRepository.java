package com.lex.repository;

import com.lex.model.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Lex Yu
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<Users, Long> {
}

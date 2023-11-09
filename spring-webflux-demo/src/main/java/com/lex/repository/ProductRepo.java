package com.lex.repository;

import com.lex.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author : Lex Yu
 */
public interface ProductRepo extends ReactiveCrudRepository<Product, Integer> {
}

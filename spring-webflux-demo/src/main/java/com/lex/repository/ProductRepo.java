package com.lex.repository;

import com.lex.model.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author : Lex Yu
 */
public interface ProductRepo extends ReactiveCrudRepository<Product, Integer> {

    @Query("""
            UPDATE product SET name = :#{#product.name}, price = :#{#product.price} WHERE id = :id""")
    Mono<Product> updateProductById(@Param("id") Integer id, @Param("product") Product product);
}

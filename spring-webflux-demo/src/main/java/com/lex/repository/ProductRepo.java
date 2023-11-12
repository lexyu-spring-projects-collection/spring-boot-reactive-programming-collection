package com.lex.repository;

import com.lex.model.Product;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author : Lex Yu
 */
public interface ProductRepo extends ReactiveCrudRepository<Product, Integer> {

    @Modifying
    @Query("""
            UPDATE product SET name = :name, price = :price WHERE id = :id""")
    Mono<Long> updateProductById(@Param("id") Integer id, @Param("name") String name, @Param("price") String price);
}

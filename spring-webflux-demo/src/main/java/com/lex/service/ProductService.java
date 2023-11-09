package com.lex.service;

import com.lex.model.Product;
import com.lex.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author : Lex Yu
 */
@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Mono<ResponseEntity> insertProduct(Product product){
        return productRepo.save(product)
                .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p))
                .cast(ResponseEntity.class)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}

package com.lex.service;

import com.lex.model.Product;
import com.lex.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author : Lex Yu
 */
@Slf4j
@Service
public class ProductService {

	private final ProductRepo productRepo;

	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	public Mono<ResponseEntity> insertProduct(Product product) {
		return productRepo.save(product)
				.log()
				.map(p -> {
					log.info("Product Id = {}", p.getId());
					return ResponseEntity.status(HttpStatus.CREATED).body(p);
				})
				.cast(ResponseEntity.class)
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatus.BAD_REQUEST.getReasonPhrase()));
	}

	public Mono<ResponseEntity> insertBatchOfProducts(List<Product> products) {
		return productRepo.saveAll(products)
				.log()
				.collectList()
				.map(productList -> ResponseEntity.status(HttpStatus.CREATED).body(productList))
				.cast(ResponseEntity.class)
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatus.BAD_REQUEST.getReasonPhrase()));
	}

	public Mono<ResponseEntity> getProduct(Integer id) {
		return productRepo.findById(id)
				.log()
				.onErrorContinue((e, data) -> {
					log.error("Err = {}", e.getMessage());
					log.error("Data = {}", data);
				})
				.map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p))
				.cast(ResponseEntity.class)
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found"));
	}

	public Mono<ResponseEntity> getProducts() {
		return productRepo.findAll()
				.log()
				.collectList()
				.map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p))
				.cast(ResponseEntity.class)
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	public Mono<ResponseEntity> updateProduct() {
		return null;
	}

	public Mono<ResponseEntity> updateBatchOfProducts() {
		return null;
	}

	public Mono<ResponseEntity> deleteProduct() {
		return null;
	}

	public Mono<ResponseEntity> deleteBatchOfProducts() {
		return null;
	}
}

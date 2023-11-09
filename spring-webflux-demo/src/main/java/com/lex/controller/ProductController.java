package com.lex.controller;

import com.lex.model.Product;
import com.lex.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author : Lex Yu
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity> getProduct(@PathVariable(value = "id") Integer id) {
		return productService.getProduct(id);
	}

	@GetMapping("/list")
	public Mono<ResponseEntity> getProducts() {
		return productService.getProducts();
	}

	@PostMapping
	public Mono<ResponseEntity> saveProduct(@RequestBody Product product) {
		return productService.insertProduct(product);
	}

	@PostMapping("/batch")
	public Mono<ResponseEntity> saveProducts(@RequestBody List<Product> products) {
		return productService.insertBatchOfProducts(products);
	}

	@PutMapping
	public Mono<ResponseEntity> updateProduct() {
		return productService.updateProduct();
	}

	@PutMapping("/batch")
	public Mono<ResponseEntity> updateProducts() {
		return productService.updateBatchOfProducts();
	}

	@DeleteMapping
	public Mono<ResponseEntity> deleteProduct() {
		return productService.deleteProduct();
	}

	@DeleteMapping("/batch")
	public Mono<ResponseEntity> deleteProducts() {
		return productService.deleteBatchOfProducts();
	}
}

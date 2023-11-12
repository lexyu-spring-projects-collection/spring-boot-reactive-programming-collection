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

	@PutMapping("/{id}")
	public Mono<ResponseEntity> updateProduct(@PathVariable(value = "id") Integer id,
											  @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}
	@PutMapping("/query/{id}")
	public Mono<ResponseEntity> updateProduct2(@PathVariable(value = "id") Integer id,
											  @RequestBody Product product) {
		return productService.updateProductByQuery(id, product);
	}

	@PutMapping("/batch")
	public Mono<ResponseEntity> updateProducts() {
		return productService.updateBatchOfProducts();
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity> deleteProduct(@PathVariable(value = "id") Integer id) {
		return productService.deleteProduct(id);
	}

	@DeleteMapping("/batch")
	public Mono<ResponseEntity> deleteProducts(@RequestBody List<Integer> idList) {
		return productService.deleteBatchOfProducts(idList);
	}
}

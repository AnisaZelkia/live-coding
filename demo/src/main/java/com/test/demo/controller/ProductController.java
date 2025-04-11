package com.test.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.model.request.CreateProductRequest;
import com.test.demo.model.request.UpdateProductRequest;
import com.test.demo.persistence.entity.Product;
import com.test.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		return ResponseEntity.ok(productService.getEntityById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(productService.getAll());
	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody CreateProductRequest request) {
		productService.add(request);
		return ResponseEntity.ok("Berhasil Menambahkan Produk");
	}
	
	@PutMapping
	public ResponseEntity<String> edit(@RequestBody UpdateProductRequest request) {
		productService.edit(request);
		return ResponseEntity.ok("Berhasil Menambahkan Produk");
	}

	@DeleteMapping
	public ResponseEntity<String> deleteByIds(@RequestBody List<Integer> ids) {
		productService.deleteByIds(ids);
		return ResponseEntity.ok("Berhasil Delete Produk");
	}

}

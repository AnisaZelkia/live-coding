package com.test.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.demo.model.request.CreateProductRequest;
import com.test.demo.model.request.UpdateProductRequest;
import com.test.demo.persistence.entity.Product;
import com.test.demo.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository repo;

	public void add(CreateProductRequest createProductRequest) {
		Product entity = new Product();
		entity.setName(createProductRequest.getName());
		entity.setQuantity(createProductRequest.getQuantity());
		repo.save(entity);
	}

	public void edit(UpdateProductRequest updateProductRequest) {
		Optional<Product> product = repo.findById(updateProductRequest.getId());
		if (product.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product tidak ada");
		}
		Product entity = new Product();
		entity.setName(updateProductRequest.getName());
		entity.setQuantity(updateProductRequest.getQuantity());
		repo.saveAndFlush(entity);
	}

	public Optional<Product> getEntityById(int id) {
		return repo.findById(id);
	}

	public void deleteById(int id) {
		Optional<Product> product = repo.findById(id);
		if (product.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product tidak ada");
		}
		repo.delete(product.get());
	}

	public List<Product> getAll() {
		return repo.findAll();
	}
}

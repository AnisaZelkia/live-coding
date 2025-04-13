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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository repo;

	private void setEntity(Product entity, CreateProductRequest request) {
		entity.setName(request.getName());
		entity.setQuantity(request.getQuantity());
		entity.setPrice(request.getPrice());
	}

	@Transactional
	public void add(CreateProductRequest request) {
		Product entity = new Product();
		setEntity(entity, request);
		repo.save(entity);
	}

	@Transactional
	public void edit(UpdateProductRequest request) {
		Product product = getEntityById(request.getId());
		setEntity(product, request);
		repo.saveAndFlush(product);
	}

	public Product getEntityById(int id) {
		Optional<Product> product = repo.findById(id);
		if (product.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product tidak ada");
		}
		return product.get();
	}

	@Transactional
	public void deleteById(int id) {
		repo.delete(getEntityById(id));
	}

	@Transactional
	public void deleteByIds(List<Integer> ids) {
		ids.stream().forEach(this::deleteById);
	}

	public List<Product> getAll() {
		return repo.findAll();
	}
}

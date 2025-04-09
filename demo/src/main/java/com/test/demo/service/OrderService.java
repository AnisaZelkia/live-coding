package com.test.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.demo.persistence.entity.Order;
import com.test.demo.persistence.entity.Product;
import com.test.demo.persistence.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repo;
	private final ProductService productService;

	public void add(Order order) {
		Optional<Product> product = productService.getEntityById(order.getProduct().getId());
		if (product.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produk tidak ditemukan");
		}
		Product productData = product.get();
		if (productData.getQuantity() < order.getQuantity()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stok tidak tersedia");
		}
		repo.save(order);
	}

	public void edit(Order order) {
		Optional<Order> existingOrder = repo.findById(order.getId());
		if (existingOrder.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order tidak ditemukan");
		}
		Order entity = existingOrder.get();
		entity.setStatus("Finished");
		repo.saveAndFlush(entity);
	}

	public Optional<Order> getEntityById(int id) {
		return repo.findById(id);
	}

	public void deleteById(int id) {
		Optional<Order> order = repo.findById(id);
		if (order.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product tidak ada");
		}
		repo.delete(order.get());
	}

	public List<Order> getAll() {
		return repo.findAll();
	}

}

package com.test.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.demo.model.request.CreateOrderRequest;
import com.test.demo.model.request.UpdateOrderRequest;
import com.test.demo.persistence.entity.Order;
import com.test.demo.persistence.entity.Product;
import com.test.demo.persistence.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repo;
	private final ProductService productService;

	private void setEntity(Order entity, CreateOrderRequest request) {
		Product product = productService.getEntityById(request.getProductId());
		if (product.getQuantity() < request.getQuantity()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stok tidak tersedia");
		}
		entity.setProduct(product);
		entity.setQuantity(request.getQuantity());
		entity.setTotalPrice(request.getQuantity() * product.getPrice());
		entity.setStatus(request.getStatus());
	}

	@Transactional
	public void add(CreateOrderRequest request) {
		Order entity = new Order();
		setEntity(entity, request);
		repo.save(entity);
	}

	@Transactional
	public void edit(UpdateOrderRequest request) {
		Optional<Order> existingOrder = repo.findById(request.getId());
		if (existingOrder.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order tidak ditemukan");
		}
		Order entity = existingOrder.get();
		setEntity(entity, request);
		repo.saveAndFlush(entity);
	}

	@Transactional
	public void deleteById(int id) {
		repo.delete(getEntityById(id));
	}

	@Transactional
	public void deleteByIds(List<Integer> ids) {
		ids.stream().forEach(this::deleteById);
	}

	public List<Order> getAll() {
		return repo.findAll();
	}

	public Order getEntityById(int id) {
		Optional<Order> order = repo.findById(id);
		if (order.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product tidak ada");
		}
		return order.get();
	}

}

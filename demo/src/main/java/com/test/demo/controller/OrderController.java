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

import com.test.demo.model.request.CreateOrderRequest;
import com.test.demo.model.request.UpdateOrderRequest;
import com.test.demo.persistence.entity.Order;
import com.test.demo.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<String> add(CreateOrderRequest order) {
		orderService.add(order);
		return ResponseEntity.ok("Berhasil Menambahkan Order");
	}

	@PutMapping
	public ResponseEntity<String> edit(UpdateOrderRequest order) {
		orderService.edit(order);
		return ResponseEntity.ok("Berhasil Edit Order");
	}

	@DeleteMapping
	public ResponseEntity<String> deleteByIds(@RequestBody List<Integer> ids ) {
		orderService.deleteByIds(ids);
		return ResponseEntity.ok("Berhasil Delete Order");
	}
	
	@GetMapping
	public ResponseEntity<Order> getProductById(@PathVariable int id) {
		return ResponseEntity.ok(orderService.getEntityById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Order>> getAll() {
		return ResponseEntity.ok(orderService.getAll());
	}
}

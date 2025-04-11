package com.test.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class CreateOrderRequest {
	private Integer productId;
	private String trxNumber;
	private Integer quantity;
	private String status;

}

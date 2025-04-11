package com.test.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UpdateOrderRequest extends CreateOrderRequest{
	private int id ;
	private int version;


}

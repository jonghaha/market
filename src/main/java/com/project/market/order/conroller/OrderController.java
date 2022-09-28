package com.project.market.order.conroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.market.common.model.CommonResponse;
import com.project.market.order.application.OrderService;
import com.project.market.order.domain.Order;
import com.project.market.order.domain.OrderDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public CommonResponse<Order> order(@RequestBody OrderDto orderDto) {
		return orderService.order(orderDto);
	}
}

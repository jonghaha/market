package com.project.market.order.application;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.market.common.event.Events;
import com.project.market.common.model.CommonResponse;
import com.project.market.member.domain.Member;
import com.project.market.order.domain.Order;
import com.project.market.order.domain.OrderCompleteNotifyEvent;
import com.project.market.order.domain.OrderDetail;
import com.project.market.order.domain.OrderDto;
import com.project.market.order.domain.OrderProduct;
import com.project.market.order.domain.OrderRepository;
import com.project.market.order.domain.OrderState;
import com.project.market.product.domain.Product;
import com.project.market.product.domain.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;

	@Transactional
	public CommonResponse<Order> order(OrderDto orderDto) {

		List<OrderDetail> orderDetails = new ArrayList<>();
		for (OrderProduct orderProduct : orderDto.getOrderProducts()) {
			Product product = productRepository.findById(orderProduct.getProductId()).orElseThrow(NoSuchElementException::new);
			orderDetails.add(OrderDetail.of(product.getId(), product.getPrice(), orderProduct.getQuantity()));
		}

		Order order = Order.of(Member.fromMemberId(orderDto.getMemberId()), orderDetails, orderDto.getAddress(), OrderState.ORDERED);
		orderRepository.save(order);
		Events.raise(new OrderCompleteNotifyEvent(orderDto.getMemberId(), "주문완료!"));

		return CommonResponse.ok(order);
	}
}

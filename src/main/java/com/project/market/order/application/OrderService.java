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
import com.project.market.order.domain.OrderDetail;
import com.project.market.order.domain.OrderDto;
import com.project.market.order.domain.OrderNotifyEvent;
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
		Events.raise(new OrderNotifyEvent(orderDto.getMemberId(), "주문완료!"));

		return CommonResponse.ok(order);
	}

	@Transactional
	public CommonResponse<Order> orderCancel(OrderDto orderDto) {

		if (orderDto.getOrderId() == null) {
			return CommonResponse.fail("유효하지 않은 주문번호입니다.");
		}

		Order order = orderRepository.findById(orderDto.getOrderId()).orElse(null);
		if (order == null) {
			return CommonResponse.fail("유효하지 않은 주문번호입니다.");
		}
		order.cancel();
		Events.raise(new OrderNotifyEvent(orderDto.getMemberId(), "주문취소완료!"));

		return CommonResponse.ok(order);
	}

	@Transactional
	public CommonResponse<Order> orderPartiallyCancel(OrderDto orderDto) {

		if (orderDto.getOrderId() == null) {
			return CommonResponse.fail("유효하지 않은 주문번호입니다.");
		}
		for (OrderProduct orderProduct : orderDto.getOrderProducts()) {
			Product product = productRepository.findById(orderProduct.getProductId()).orElseThrow(NoSuchElementException::new);
			orderProduct.setPrice(product.getPrice());
		}

		Order order = orderRepository.findById(orderDto.getOrderId()).orElse(null);
		if (order == null) {
			return CommonResponse.fail("유효하지 않은 주문번호입니다.");
		}
		order.partiallyCancel(orderDto);
		Events.raise(new OrderNotifyEvent(orderDto.getMemberId(), "주문부분취소완료!"));

		return CommonResponse.ok(order);
	}
}

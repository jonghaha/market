package com.project.market.order.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	private String memberId;
	private List<OrderProduct> orderProducts;
	private Address address;
}
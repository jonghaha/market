package com.project.market.order.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderProduct {
	private Long productId;
	private int quantity;
}

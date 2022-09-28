package com.project.market.order.domain;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.market.product.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class OrderDetail {
	private Long productId;
	private Integer price;
	private Integer quantity;
	private Integer amounts;
	@Builder.Default
	private Boolean canceled = Boolean.FALSE;

	public static OrderDetail of(Long productId, Integer price, Integer quantity) {
		return OrderDetail.builder()
			.productId(productId)
			.price(price)
			.quantity(quantity)
			.amounts(price * quantity)
			.build();
	}

	public void cancel() {
		this.canceled = Boolean.TRUE;
	}
}
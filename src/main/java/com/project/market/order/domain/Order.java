package com.project.market.order.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.market.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "order_detail", joinColumns = @JoinColumn(name = "order_id"))
	private List<OrderDetail> orderDetails;

	private Integer totalAmounts;

	@Embedded
	private Address address;

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private OrderState orderState;

	@Column(name = "order_date")
	@Builder.Default
	private LocalDateTime orderDate = LocalDateTime.now();

	public static Order of(Member member, List<OrderDetail> orderDetails, Address address, OrderState orderState) {
		return Order.builder()
			.member(member)
			.orderDetails(orderDetails)
			.address(address)
			.orderState(orderState)
			.totalAmounts(orderDetails.stream().mapToInt(OrderDetail::getAmounts).sum())
			.build();
	}

	public void cancel() {
		this.orderState = OrderState.CANCELED;
		for (OrderDetail detail : this.orderDetails) {
			detail.cancel();
		}
	}

	public void partiallyCancel(OrderDto orderDto) {
		this.orderState = OrderState.PARTIALLY_CANCELED;
		for (OrderDetail detail : this.orderDetails) {
			for (OrderProduct orderProduct : orderDto.getOrderProducts()) {
				if (detail.getProductId().equals(orderProduct.getProductId())) {
					detail.cancel();
					this.totalAmounts = this.totalAmounts - detail.getAmounts();
				}
			}
		}
	}
}

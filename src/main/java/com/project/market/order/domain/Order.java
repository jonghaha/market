package com.project.market.order.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name = "order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "order_detail", joinColumns = @JoinColumn(name = "order_seq"))
	private List<OrderDetail> orderDetails;

	private Integer totalAmounts;

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private OrderState state;

	@Column(name = "order_date")
	@Builder.Default
	private LocalDateTime orderDate = LocalDateTime.now();
}

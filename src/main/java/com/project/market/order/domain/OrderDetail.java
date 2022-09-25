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

@Embeddable
public class OrderDetail {
	private Integer price;
	private Integer quantity;
	private Integer amounts;
}
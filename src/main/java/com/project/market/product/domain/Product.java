package com.project.market.product.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.market.common.application.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	private String name;

	private Integer price;

	private String description;

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
	private List<Image> images = new ArrayList<>();

	public static Product mapToEntity(ProductDto productDto) {
		return Product.builder()
			.name(productDto.getName())
			.price(productDto.getPrice())
			.description(productDto.getDescription())
			.images(productDto.getImagesUrl().stream().map(Image::of).collect(Collectors.toList()))
			.build();
	}

	public void update(ProductDto productDto) {
		if (productDto.getName() != null || CommonUtil.isDifferent(this.name, productDto.getName())) {
			this.name = productDto.getName();
		}
		if (productDto.getPrice() != null || CommonUtil.isDifferent(this.price, productDto.getPrice())) {
			this.price = productDto.getPrice();
		}
		if (productDto.getDescription() != null || CommonUtil.isDifferent(this.description, productDto.getDescription())) {
			this.description = productDto.getDescription();
		}
	}
}

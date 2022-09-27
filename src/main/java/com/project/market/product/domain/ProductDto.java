package com.project.market.product.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private Long id;
	private String name;
	private Integer price;
	private String description;
	private List<String> imagesUrl;
}
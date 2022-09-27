package com.project.market.product.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.market.common.model.CommonResponse;
import com.project.market.product.application.ProductService;
import com.project.market.product.domain.Product;
import com.project.market.product.domain.ProductDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@PostMapping("/insert")
	public CommonResponse<Product> insertProduct(@RequestBody ProductDto productDto) {
		return productService.insertProduct(productDto);
	}

	@PostMapping("/update")
	public CommonResponse<Product> updateProduct(@RequestBody ProductDto productDto) {
		return productService.updateProduct(productDto);
	}

	@PostMapping("/delete")
	public CommonResponse deleteProduct(@RequestBody ProductDto productDto) {
		return productService.deleteProduct(productDto);
	}
}

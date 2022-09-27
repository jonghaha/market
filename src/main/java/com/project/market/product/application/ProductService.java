package com.project.market.product.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.market.common.model.CommonResponse;
import com.project.market.product.domain.Product;
import com.project.market.product.domain.ProductDto;
import com.project.market.product.domain.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	private Product findProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Transactional
	public CommonResponse<Product> insertProduct(ProductDto productDto) {
		return CommonResponse.ok(productRepository.save(Product.mapToEntity(productDto)));
	}

	@Transactional
	public CommonResponse<Product> updateProduct(ProductDto productDto) {

		if (productDto.getId() == null) {
			return CommonResponse.fail("해당 상품은 존재하지 않습니다.");
		}

		Product product = findProduct(productDto.getId());
		if (product == null) {
			return CommonResponse.fail("해당 상품은 존재하지 않습니다.");
		}

		product.update(productDto);

		return CommonResponse.ok(product);
	}

	@Transactional
	public CommonResponse deleteProduct(ProductDto productDto) {

		if (productDto.getId() == null) {
			return CommonResponse.fail("해당 상품은 존재하지 않습니다.");
		}

		Product product = findProduct(productDto.getId());
		if (product == null) {
			return CommonResponse.fail("해당 상품은 존재하지 않습니다.");
		}

		productRepository.delete(product);

		return CommonResponse.ok();
	}
}

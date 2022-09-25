package com.project.market.product.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	@Transactional
	void save() {

		List<Image> images = new ArrayList<>();
		images.add(Image.builder().url("https://test.com/1").build());
		images.add(Image.builder().url("https://test.com/2").build());

		Product product = Product.builder()
			.name("상품1")
			.price(10000)
			.description("상품1 내용")
			.images(images)
			.build();
		productRepository.save(product);

		Product searchProduct = productRepository.findById(1L).orElseThrow();
		assertThat(searchProduct.getPrice()).isEqualTo(10000);
		for (Image image : searchProduct.getImages()) {
			System.out.println(image.getUrl());
		}
	}
}

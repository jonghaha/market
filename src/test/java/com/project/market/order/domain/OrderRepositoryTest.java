package com.project.market.order.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.market.member.domain.Member;
import com.project.market.member.domain.MemberRepository;
import com.project.market.product.domain.Image;
import com.project.market.product.domain.Product;
import com.project.market.product.domain.ProductRepository;

@SpringBootTest
public class OrderRepositoryTest {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		Member member = Member.builder().id("id01").name("아이디1").password("12345").build();
		memberRepository.save(member);

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
	}

	@Transactional
	@Test
	void save() {
		Member member = memberRepository.findById("id01").orElseThrow();
		Product product = productRepository.findById(1L).orElseThrow();

		orderRepository.save(Order.of(member, List.of(OrderDetail.of(product.getId(), 2, product.getPrice())),
			OrderState.PAYMENT_WAITING));

		Order findOrder = orderRepository.findById(1L).orElseThrow();

		assertThat(findOrder.getOrderDetails().get(0).getAmounts()).isEqualTo(2 * product.getPrice());
		assertThat(findOrder.getTotalAmounts()).isEqualTo(findOrder.getOrderDetails().get(0).getAmounts());
	}
}

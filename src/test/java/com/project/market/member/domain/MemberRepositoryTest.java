package com.project.market.member.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Test
	void save() {
		Member member = Member.builder().id("id01").name("아이디1").password("12345").build();
		memberRepository.save(member);

		Member findMember = memberRepository.findById("id01").orElseThrow();

		assertThat(findMember.getName()).isEqualTo("아이디1");
	}
}

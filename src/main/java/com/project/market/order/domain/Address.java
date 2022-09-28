package com.project.market.order.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Address {
	private String zipCode;
	private String address1;
	private String address2;
}

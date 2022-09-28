package com.project.market.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderNotifyEvent {
	String memberId;
	String message;
}

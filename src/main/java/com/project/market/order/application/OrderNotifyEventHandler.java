package com.project.market.order.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.project.market.notification.application.NotificationService;
import com.project.market.order.domain.OrderNotifyEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderNotifyEventHandler {

	private final NotificationService notificationService;

	@EventListener(OrderNotifyEvent.class)
	public void notifyComplete(OrderNotifyEvent event) {
		notificationService.SendNotification(event.getMemberId(), event.getMessage());
	}
}

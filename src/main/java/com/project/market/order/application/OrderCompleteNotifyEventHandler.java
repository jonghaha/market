package com.project.market.order.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.project.market.notification.application.NotificationService;
import com.project.market.order.domain.OrderCompleteNotifyEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderCompleteNotifyEventHandler {

	private final NotificationService notificationService;

	@EventListener(OrderCompleteNotifyEventHandler.class)
	public void notifyComplete(OrderCompleteNotifyEvent event) {
		notificationService.SendNotification(event.getMemberId(), event.getMessage());
	}
}

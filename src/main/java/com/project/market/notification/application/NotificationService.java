package com.project.market.notification.application;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public void SendNotification(String memberId, String message) {
		System.out.println(memberId);
		System.out.println(message);
	}
}

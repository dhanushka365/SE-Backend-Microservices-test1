package com.service.clients.notification;

public record NotificationRequest(
        Integer toStudentId,
        String toStudentName,
        String message
) {
}

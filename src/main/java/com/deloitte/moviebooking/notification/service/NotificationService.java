package com.deloitte.moviebooking.notification.service;

import com.deloitte.moviebooking.notification.model.Notification;
import com.deloitte.moviebooking.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing notifications.
 */
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void notifyUser(String userId, String message) {
        notificationRepository.save(new Notification(userId, message));
    }

    public List<Notification> getUserNotifications(String userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}

package com.deloitte.moviebooking.notification.repository;

import com.deloitte.moviebooking.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Notification entity.
 */
public interface NotificationRepository extends JpaRepository<Notification, String> {

    List<Notification> findByUserIdOrderByCreatedAtDesc(String userId);
}

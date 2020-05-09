package com.socblog.repo;

import com.socblog.dto.NotificationDTO;
import com.socblog.models.Notification;
import com.socblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {



}

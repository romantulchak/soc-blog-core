package com.socblog.repo;

import com.socblog.dto.NotificationBoxDTO;
import com.socblog.models.NotificationBox;
import com.socblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationBoxRepo extends JpaRepository<NotificationBox, Long> {

    @Query(value ="SELECT new com.socblog.dto.NotificationBoxDTO(n) FROM NotificationBox n where n = ?1 order by n.id DESC ")
    NotificationBoxDTO fin(NotificationBox box);

}

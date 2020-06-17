package com.socblog.repo;

import com.socblog.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

    @Query(value = "SELECT i FROM Image i where i.user.id = :userId order by i.id DESC ")
    List<Image> findAllForUser(@Param("userId") Long userId);

}

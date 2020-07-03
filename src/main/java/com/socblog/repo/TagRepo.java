package com.socblog.repo;

import com.socblog.dto.TagDTO;
import com.socblog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {

    @Query(value = "SELECT new com.socblog.dto.TagDTO(t) FROM Tag t order by t.id DESC ")
    List<TagDTO> tags();

    Boolean existsByName(String name);

    List<Tag> findAllByNameContaining(String str);
}

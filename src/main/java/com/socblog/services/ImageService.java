package com.socblog.services;

import com.socblog.models.Image;
import com.socblog.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImageService {
    List<Image> getImages(Long id);
    ResponseEntity<?> removeImage(Image image, User user);
}

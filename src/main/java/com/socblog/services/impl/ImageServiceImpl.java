package com.socblog.services.impl;

import com.socblog.models.Image;
import com.socblog.models.User;
import com.socblog.repo.ImageRepo;
import com.socblog.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepo imageRepo;


    @Autowired
    public ImageServiceImpl(ImageRepo imageRepo){
        this.imageRepo = imageRepo;
    }



    @Override
    public List<Image> getImages(Long id) {
        return imageRepo.findAllForUser(id);
    }

    @Override
    public ResponseEntity<?> removeImage(Image image, User user) {
        if(image != null && user != null){
            imageRepo.delete(image);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something wrong", HttpStatus.OK);
    }
}

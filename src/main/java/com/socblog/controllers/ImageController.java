package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Image;
import com.socblog.models.User;
import com.socblog.models.Views;
import com.socblog.services.ImageService;
import com.socblog.services.impl.ImageServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(value = "*", maxAge = 3600)
public class ImageController {

    private ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService){
        this.imageService = imageService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.UserFull.class)
    public List<Image> images(@RequestParam(value = "userId") Long id){
        return imageService.getImages(id);
    }


    @DeleteMapping("/delete/{imageId}/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> removeImage(@PathVariable("imageId") Image image, @PathVariable("userId")User user){
        return imageService.removeImage(image, user);
    }



}

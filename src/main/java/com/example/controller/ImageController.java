package com.example.controller;

import com.example.entity.Bad;
import com.example.entity.Image;
import com.example.entity.Property;
import com.example.repository.ImageRepository;
import com.example.repository.PropertyRepository;
import com.example.service.BucketService;
import com.example.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("/api/v1/image")
@AllArgsConstructor
public class ImageController {
    private ImageRepository imageRepository;
    private PropertyService propertyService;
    private BucketService bucketService;

    @PostMapping(value = "/upload/file/{bucketName}/property/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(
            @RequestParam MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable long propertyId,
            @AuthenticationPrincipal Bad bad
    ) {
        String imageUrl = bucketService.uploadFile(file, bucketName);
        Property property = propertyService.findById(propertyId);
        Image img = new Image();
        img.setUrl(imageUrl);
        img.setProperty(property);
        Image saved = imageRepository.save(img);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }

}

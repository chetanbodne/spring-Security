package com.example.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class BucketService {
    @Autowired
    private AmazonS3 amazonS3;

    public String uploadFile(MultipartFile file, String bucketName) {
        if (file.isEmpty()) {
            throw new IllegalStateException("cannot upload file");
        }
        try {
            File convfile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convfile);
//            System.out.println(bucketName);
//            System.out.println(file.getOriginalFilename());
//            System.out.println(convfile);

            amazonS3.putObject(bucketName, file.getOriginalFilename(), convfile);
            return amazonS3.getUrl(bucketName, file.getOriginalFilename()).toString();


        } catch (AmazonS3Exception s3Exception) {
            return "unable upload file" + s3Exception.getMessage();
        } catch (Exception e) {
            throw new IllegalStateException("failed to upload file", e);
        }


    }
}

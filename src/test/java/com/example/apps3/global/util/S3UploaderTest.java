package com.example.apps3.global.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class S3UploaderTest {

    @Autowired
    private S3Uploader s3Uploader;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessValue;

    @Test
    void upload() {
        try{
            String filePath = "/Users/Img/test2.jpeg";

            String uploadName = s3Uploader.upload(filePath);

            log.info(uploadName);

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Test
    void removeS3File() {
        log.info(accessValue);

    }
}
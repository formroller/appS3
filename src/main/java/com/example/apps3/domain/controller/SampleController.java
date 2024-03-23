package com.example.apps3.domain.controller;

import com.example.apps3.domain.dto.SampleDTO;
import com.example.apps3.global.util.LocalUploader;
import com.example.apps3.global.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/sample")
public class SampleController {

    private final LocalUploader localUploader;
    private final S3Uploader s3Uploader;

    @PostMapping("/upload")
    public List<String> upload(SampleDTO sampleDTO){
        log.info(sampleDTO);


        MultipartFile[] files = sampleDTO.getFiles();
        if(files == null || files.length <= 0){
            return null;
        }

        log.info(files);
        List<String> uploadedFilePaths = new ArrayList<>();

        for(MultipartFile file:files){
            uploadedFilePaths.addAll(localUploader.uploadLocal(file));
        }
        
        log.info("============================");
        log.info(uploadedFilePaths);
        
        List<String> s3Paths =
            uploadedFilePaths.stream().map(fileName -> s3Uploader.
                upload(fileName)).collect(Collectors.toList());

        return s3Paths;
    }
}

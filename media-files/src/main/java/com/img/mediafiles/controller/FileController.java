package com.img.mediafiles.controller;

import com.img.mediafiles.model.FileData;
import com.img.mediafiles.model.ImageData;
import com.img.mediafiles.service.FileDataService;
import com.img.mediafiles.service.ImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/file-data")
public class FileController {
    @Autowired
    private FileDataService fileDataService;

    @Autowired
    private ImageDataService imageDataService;

    @PostMapping("/upload-media")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file){
        String filepath=fileDataService.uploadFileToSystemFolder(file);
        return ResponseEntity.status(HttpStatus.OK).body(filepath);
    }
    @GetMapping("/download-media")
    public ResponseEntity<?> downloadImage(@RequestParam("imageName") String imageName) throws IOException {
        FileData fileData =fileDataService.downloadFileFromSystemFolder(imageName);
        String filePath=fileData.getFilepath();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(fileData.getFiletype()))
                .body(Files.readAllBytes(new File(filePath).toPath()));
    }

    @PostMapping("/upload-media-database")
    public ResponseEntity<?> mediaUploadToDatabase(@RequestParam("file") MultipartFile file){
        String image=imageDataService.uploadMedia(file);
        return ResponseEntity.status(HttpStatus.OK).body(image);
    }

    @GetMapping("/download-media-database")
    public ResponseEntity<?> downloadImageFromDatabase(@RequestParam("filename") String filename){
        ImageData imageData=imageDataService.downloadMedia(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(imageData.getFiletype()))
                .body(imageData.getImage());
    }
}

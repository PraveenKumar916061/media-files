package com.img.mediafiles.service;

import com.img.mediafiles.model.ImageData;
import com.img.mediafiles.repository.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepository  imageDataRepository;

    public String uploadMedia(MultipartFile file){
        try {
            ImageData imageData=imageDataRepository.save(ImageData.builder()
                    .filename(file.getOriginalFilename())
                    .filetype(file.getContentType())
                    .image(file.getBytes()).build());
            if(imageData!=null)
                return "image uploaded";
            else
                return "Not uploaded";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ImageData downloadMedia(String filename){
        Optional<ImageData> imageData=imageDataRepository.findByFilename(filename);
        //byte[] image=.getImage();
        return imageData.get();
    }

}

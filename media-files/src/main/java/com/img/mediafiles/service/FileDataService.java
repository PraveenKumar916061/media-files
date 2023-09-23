package com.img.mediafiles.service;

import com.img.mediafiles.model.FileData;
import com.img.mediafiles.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileDataService {
    @Autowired
    private FileDataRepository fileDataRepository;
    private final static String FILE_PATH="C:/Media files/";

    public String uploadFileToSystemFolder(MultipartFile file){
        String filePath=FILE_PATH+file.getOriginalFilename();
        try {
            FileData fileData=fileDataRepository.save(FileData.builder()
                    .filename(file.getOriginalFilename())
                    .filetype(file.getContentType())
                    .filepath(filePath).build());
            file.transferTo(new File(filePath));

            if(fileData!=null)
                return "file uploaded successfully "+filePath;
            else
                return "file not uploaded";
        } catch (IOException e) {
            throw new RuntimeException("invalid file path");
        }
    }

    public FileData downloadFileFromSystemFolder(String fileName){
        Optional<FileData> fileData=fileDataRepository.findByFilename(fileName);
        return fileData.get();
        //return filePath;
//        try {
//            byte[] image=Files.readAllBytes(new File(filePath).toPath());
//            return image;
//        } catch (IOException e) {
//            throw new RuntimeException("Invalid filename");
//        }
    }
}

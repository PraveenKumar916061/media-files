package com.img.mediafiles.repository;

import com.img.mediafiles.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData,Integer> {
    Optional<ImageData> findByFilename(String filename);
}

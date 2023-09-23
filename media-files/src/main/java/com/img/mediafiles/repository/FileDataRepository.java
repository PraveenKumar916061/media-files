package com.img.mediafiles.repository;

import com.img.mediafiles.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData,Integer> {

    Optional<FileData> findByFilename(String fileName);
}

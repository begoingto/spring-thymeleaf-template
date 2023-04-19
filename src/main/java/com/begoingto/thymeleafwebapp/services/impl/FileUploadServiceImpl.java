package com.begoingto.thymeleafwebapp.services.impl;

import com.begoingto.thymeleafwebapp.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${file.server_path}")
    private String fileServerPath;
    @Override
    public boolean uploadSingle(MultipartFile file) {
        // gddd-dfv-db-fdf-df-.jpg
        int index = file.getOriginalFilename().lastIndexOf('.');
        String ext = file.getOriginalFilename().substring(index+1);
        System.out.println(ext);
        System.out.println("File upload");
        String uuid = UUID.randomUUID().toString();
        String newFile = String.format("%s%s%s",uuid,".",ext);

        Path paths = Paths.get(fileServerPath + newFile);
        try {
            Files.copy(file.getInputStream(),paths);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

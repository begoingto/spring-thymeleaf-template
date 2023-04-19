package com.begoingto.thymeleafwebapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    boolean uploadSingle(MultipartFile file);
}

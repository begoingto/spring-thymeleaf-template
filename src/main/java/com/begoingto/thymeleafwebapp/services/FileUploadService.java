package com.begoingto.thymeleafwebapp.services;

import com.begoingto.thymeleafwebapp.models.FileUpload;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileUpload uploadSingle(MultipartFile file);
}

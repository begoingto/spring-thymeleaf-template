package com.begoingto.thymeleafwebapp.services.impl;

import com.begoingto.thymeleafwebapp.models.Article;
import com.begoingto.thymeleafwebapp.models.Author;
import com.begoingto.thymeleafwebapp.models.FileUpload;
import com.begoingto.thymeleafwebapp.repositories.StaticRepository;
import com.begoingto.thymeleafwebapp.services.AuthorService;
import com.begoingto.thymeleafwebapp.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final StaticRepository staticRepository;
    private final FileUploadService fileUploadService;
    @Override
    public List<Author> getAuthors() {
        return staticRepository.getAuthors();
    }

    @Override
    public Author saveAuthor(Author author, MultipartFile profile, MultipartFile cover) {
        FileUpload fileProfile = getFileUpload(profile);
        FileUpload fileCover = getFileUpload(cover);
        if (fileProfile.isSuccess() && fileCover.isSuccess()){
            author.setId(staticRepository.getAuthors().size()+1);
            author.setAvatar("/files/" + fileProfile.fileName());
            author.setCover("/files/" + fileCover.fileName());
            staticRepository.getAuthors().add(0,author);
            System.out.println("Author create avatar & cover successful");
            return author;
        }
        return null;
    }

    private FileUpload getFileUpload(MultipartFile file) {
        return fileUploadService.uploadSingle(file);
    }

    @Override
    public Author getAuthorById(Integer id) {
        return staticRepository.getAuthors().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

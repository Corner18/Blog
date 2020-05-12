package ru.itis.blog.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.FileInfo;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jdbc.FileInfoRepository;
import ru.itis.blog.repositories.jdbc.UsersRepository;
import ru.itis.blog.services.EmailService;
import ru.itis.blog.services.FileStorageService;


import java.util.Optional;

@Component
public class FileUploadEmail {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    EmailService emailService;

    public void sendEmail(String storageFileName) {
        Optional<FileInfo> fileInfoOptional = fileInfoRepository.findOneByStorageFileName(storageFileName);
        if (fileInfoOptional.isPresent()) {
            FileInfo fileInfo = fileInfoOptional.get();
            Optional<User> userOptional = usersRepository.find(fileInfo.getUserId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                String path = "http://localhost:8080/files/" + storageFileName;
                emailService.sendMail("Картинка"
                        , path
                        , user.getEmail());
            }
        }
    }
}


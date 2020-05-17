package ru.itis.blog.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.FileInfo;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jpa.FileInfoRepository;
import ru.itis.blog.repositories.jpa.UsersRepository;
import ru.itis.blog.services.EmailService;
import ru.itis.blog.services.FileStorageService;

import java.util.Optional;

@Component
public class FileUploadEmail {

    @Autowired
    private FileStorageService fileStorageService;

    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    EmailService emailService;

    public void sendEmail(String storageFileName) {
        String path = storagePath + storageFileName;
        FileInfo fileInfo = fileInfoRepository.findByUrl(path);
        Optional<User> userOptional = usersRepository.find(fileInfo.getUser().getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String link = "http://localhost:8080/files/" + storageFileName;
            emailService.sendMail("Картинка"
                    , link
                    , user.getEmail());
        }
    }
}

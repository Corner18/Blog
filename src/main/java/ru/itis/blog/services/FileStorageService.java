package ru.itis.blog.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.blog.models.User;


import javax.servlet.http.HttpServletResponse;

public interface FileStorageService {
    // сохраняет файл на сервере
    String saveFile(MultipartFile file, User user);

    // отправляет файл по запросу
    void writeFileToResponse(String fileName, HttpServletResponse response);


}

package ru.itis.blog.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.blog.dto.FileInfoDto;
import ru.itis.blog.models.User;


import javax.servlet.http.HttpServletResponse;

public interface FileStorageService {
    // сохраняет файл на сервере
    void saveFile(MultipartFile file, User user);

    // отправляет файл по запросу
    void writeFileToResponse(String fileName, HttpServletResponse response);

    // выводит файл по айди юзера
    String takeUrl(Long userId);

    FileInfoDto getFileInfo(String filename);

}

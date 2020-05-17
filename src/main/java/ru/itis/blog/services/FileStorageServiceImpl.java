package ru.itis.blog.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.blog.models.FileInfo;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jpa.FileInfoRepository;
import ru.itis.blog.repositories.jpa.UsersRepository;
import ru.itis.blog.utils.FileStorageUtil;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

@Component
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Autowired
    private UsersRepository usersRepository;

    @Override

    public String saveFile(MultipartFile file, User user) {
        FileInfo fileInfo = fileStorageUtil.convertFromMultipart(file);
        fileInfo.setUser(user);
        fileInfoRepository.save(fileInfo);
        fileStorageUtil.copyToStorage(file, fileInfo.getStorageFileName());
        user.setAvatar("http://localhost:8080/files/" + fileInfo.getStorageFileName());
        usersRepository.update(user);
        return fileInfo.getStorageFileName();
    }

    @SneakyThrows
    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        String filepath = storagePath + fileName;
        System.out.println(filepath);
        FileInfo file = fileInfoRepository.findByUrl(filepath);
        System.out.println(file);
        response.setContentType("image/jpeg");
        InputStream inputStream = new FileInputStream(new java.io.File(file.getUrl()));
        org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

}

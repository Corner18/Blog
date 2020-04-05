package ru.itis.blog.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.blog.dto.FileInfoDto;
import ru.itis.blog.models.FileInfo;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jdbc.FileInfoRepository;
import ru.itis.blog.repositories.jdbc.UsersRepository;
import ru.itis.blog.utils.FileStorageUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

@Component
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void saveFile(MultipartFile file, User user) {
        FileInfo fileInfo = fileStorageUtil.convertFromMultipart(file);
        fileInfo.setUserId(user.getId());
        fileInfoRepository.save(fileInfo);
        fileStorageUtil.copyToStorage(file, fileInfo.getStorageFileName());
        user.setAvatar("http://localhost:8080/files/" + fileInfo.getStorageFileName());
        usersRepository.update(user);

    }

    @SneakyThrows
    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = fileInfoRepository.findOneByStorageFileName(fileName).get();
        response.setContentType(file.getType());
        InputStream inputStream = new FileInputStream(new java.io.File(file.getUrl()));
        org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @Override
    public String takeUrl(Long userId) {
        Optional<FileInfo> fileInfoOptional = fileInfoRepository.findOneByUserId(userId);
        if (fileInfoOptional.isPresent()) {
            String storageName = fileInfoOptional.get().getStorageFileName();
            return storageName;
        }
        return null;
    }

    @Override
    public FileInfoDto getFileInfo(String filename) {
        FileInfo fileInfo = fileInfoRepository.findOneByStorageFileName(filename).get();
        return new FileInfoDto(fileInfo);
    }
}

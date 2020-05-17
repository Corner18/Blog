package ru.itis.blog.repositories.jpa;

import ru.itis.blog.models.FileInfo;
import ru.itis.blog.repositories.jdbc.CRUDRepository;

import java.util.Optional;

public interface FileInfoRepository  {

    void save(FileInfo fileInfo);

    FileInfo findByUrl(String url);

}


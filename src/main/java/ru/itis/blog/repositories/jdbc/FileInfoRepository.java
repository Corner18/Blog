package ru.itis.blog.repositories.jdbc;

import ru.itis.blog.models.FileInfo;

import java.util.Optional;

public interface FileInfoRepository extends CRUDRepository<FileInfo, Long> {
    Optional<FileInfo> findOneByStorageFileName(String storageFileName);

    Optional<FileInfo> findOneByUserId(Long user_id);
}


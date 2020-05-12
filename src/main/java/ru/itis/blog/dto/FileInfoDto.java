package ru.itis.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.FileInfo;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileInfoDto {
    private Long id;
    private String storageFileName;
    private Long userId;

    public FileInfoDto(FileInfo fileInfo) {
        FileInfoDto.builder()
                .id(fileInfo.getId())
                .storageFileName(fileInfo.getStorageFileName())
                .userId(fileInfo.getUserId())
                .build();
    }
}

package ru.itis.blog.models;

import lombok.*;
import org.apache.commons.io.FilenameUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "file_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@Data
@Table(name = "file_info")
public class FileInfo {

  //  private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // название файла в хранилище

    @Transient
    private String storageFileName;

    // название файла оригинальное
    @Column(name = "original_file_name")
    private String originalFileName;
    // размер файла
    @Column(name = "size")
    private Long size;
    // тип файла (MIME)
    @Column(name = "type")
    private String type;
    // по какому URL можно получить файл
    @Column(name = "url")
    private String url;
    // айди юзхера
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PostLoad
    public void loadFile() {
        storageFileName = FilenameUtils.getName(url);
        System.out.println(storageFileName);
    }

}


package ru.itis.blog.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Timestamp createdAt;
    private State state;
    private Role role;
    private String confirmCode;
    private String phoneNumber;
    private String avatar;
}




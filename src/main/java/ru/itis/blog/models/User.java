package ru.itis.blog.models;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "itis_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@Data
@Table(name = "itis_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="email")
    private String email;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Enumerated(value = EnumType.STRING)
    @Column(name="state")
    private State state;
    @Enumerated(value = EnumType.STRING)
    @Column(name="role")
    private Role role;
    @Column(name="confirm_code")
    private String confirmCode;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="avatar")
    private String avatar;

}



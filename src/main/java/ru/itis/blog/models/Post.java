package ru.itis.blog.models;


import lombok.*;

import javax.persistence.*;

@Entity(name = "post")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@Data
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String header;
    private String text;
    private Long author_id;
}

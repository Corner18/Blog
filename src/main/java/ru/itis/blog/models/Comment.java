package ru.itis.blog.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "comment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String text;

}

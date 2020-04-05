package ru.itis.blog.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "likes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}

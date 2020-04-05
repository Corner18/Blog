package ru.itis.blog.repositories.jpa;

import ru.itis.blog.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void save(Post post);
    List<Post> findAll();
    Post findById(Long id);
}

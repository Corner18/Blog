package ru.itis.blog.repositories.jpa;

import ru.itis.blog.models.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> getAllByPost_Id(Long post_id);
    void save(Comment comment);
}

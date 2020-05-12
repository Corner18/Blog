package ru.itis.blog.services;

import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.dto.CommentFreemarkerDto;

import java.util.List;

public interface CommentService {
    List<CommentFreemarkerDto> userComment(Long post_id);

    void makeComment(CommentDto form);

    List<CommentDto> getComments(Long post_id);
}

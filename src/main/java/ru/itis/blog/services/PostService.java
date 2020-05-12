package ru.itis.blog.services;


import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.dto.CommentFreemarkerDto;
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.models.Comment;
import ru.itis.blog.models.Post;

import java.util.List;

public interface PostService {
    Post getOne(Long id);
}

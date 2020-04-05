package ru.itis.blog.services;



import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.dto.CommentFreemarkerDto;
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.models.Comment;
import ru.itis.blog.models.Post;

import java.util.List;

public interface PostService {
    List<CommentFreemarkerDto> userComment(Long post_id);
    Post getOne(Long id);
    void makeComment(CommentDto form);
    void makeLike(LikeDto form);
    List<CommentDto> getComments(Long post_id);
}

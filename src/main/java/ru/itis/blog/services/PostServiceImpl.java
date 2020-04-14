package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.dto.CommentFreemarkerDto;
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.models.Comment;
import ru.itis.blog.models.Likes;
import ru.itis.blog.models.Post;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jdbc.UsersRepository;
import ru.itis.blog.repositories.jpa.CommentRepository;
import ru.itis.blog.repositories.jpa.LikeRepository;
import ru.itis.blog.repositories.jpa.PostRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post getOne(Long id) {
        Post post = postRepository.findById(id);
        return post;
    }

}

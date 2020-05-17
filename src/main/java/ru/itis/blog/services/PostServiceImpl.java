package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.blog.models.Post;
import ru.itis.blog.repositories.jpa.PostRepository;

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

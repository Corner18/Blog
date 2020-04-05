package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.Post;
import ru.itis.blog.repositories.jpa.PostRepository;


import java.util.List;

@Component
public class MainServiceImpl implements MainService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
}

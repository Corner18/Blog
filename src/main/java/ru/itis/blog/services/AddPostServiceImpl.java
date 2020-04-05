package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.blog.dto.PostDto;
import ru.itis.blog.models.Post;
import ru.itis.blog.repositories.jpa.PostRepository;

@Component
public class AddPostServiceImpl implements AddPostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void save(PostDto postDto, Long userId) {
        Post post = Post.builder()
                .author_id(userId)
                .header(postDto.getHeader())
                .text(postDto.getText())
                .build();
        postRepository.save(post);
    }
}

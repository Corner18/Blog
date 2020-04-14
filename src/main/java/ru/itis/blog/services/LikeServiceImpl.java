package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.models.Likes;
import ru.itis.blog.repositories.jpa.LikeRepository;
import ru.itis.blog.repositories.jpa.PostRepository;

@Component
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void makeLike(LikeDto form) {
        Likes like = Likes.builder()
                .post(postRepository.findById(form.getPost_id()))
                .user_id(form.getUser_id())
                .build();
        likeRepository.save(like);
    }
}

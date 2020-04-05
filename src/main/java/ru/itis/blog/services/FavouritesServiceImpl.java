package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.blog.models.Likes;
import ru.itis.blog.models.Post;
import ru.itis.blog.repositories.jpa.LikeRepository;
import ru.itis.blog.repositories.jpa.PostRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<Post> favs(Long user_id) {
        List<Likes> likes = likeRepository.getAllByUser_Id(user_id);
        List<Post> posts = new ArrayList<>();
        for (Likes like : likes){
            posts.add(like.getPost());
        }
        return posts;
    }
}

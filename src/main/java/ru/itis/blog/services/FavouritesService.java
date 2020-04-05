package ru.itis.blog.services;

import ru.itis.blog.models.Post;

import java.util.List;

public interface FavouritesService {
    List<Post> favs(Long user_id);
}

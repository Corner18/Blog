package ru.itis.blog.services;


import ru.itis.blog.models.Post;

import java.util.List;

public interface MainService {
    List<Post> getAll();
}

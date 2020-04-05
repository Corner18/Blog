package ru.itis.blog.services;

import ru.itis.blog.dto.PostDto;


public interface AddPostService {
    void save(PostDto postDto, Long userId);
}

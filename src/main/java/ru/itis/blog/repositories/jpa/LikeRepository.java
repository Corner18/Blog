package ru.itis.blog.repositories.jpa;

import ru.itis.blog.models.Likes;

import java.util.List;

public interface LikeRepository {
    List<Likes> getAllByUser_Id(Long user_id);
    void save(Likes like);
}

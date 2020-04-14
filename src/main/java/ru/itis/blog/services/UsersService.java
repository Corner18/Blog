package ru.itis.blog.services;




import ru.itis.blog.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();

    User getUser(Long userId);

    User getAdmin();

}

package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jdbc.UsersRepository;


import java.util.List;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = usersRepository.findAll();
        return users;
    }

    @Override
    public User getUser(Long userId) {
        return usersRepository.find(userId).get();
    }

}

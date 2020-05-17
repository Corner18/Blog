package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.Role;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jpa.UsersRepository;


import java.util.List;
import java.util.Optional;

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

    @Override
    public User getAdmin() {
        Optional<User> userOptional = usersRepository.findByRole(Role.ADMIN);
        return userOptional.orElse(null);
    }
}

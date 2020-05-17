package ru.itis.blog.repositories.jpa;


import ru.itis.blog.models.Role;
import ru.itis.blog.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository  {
    Optional<User> findByConfirmCode(String token);

    void update(User user);

    void save(User user);

    List<User> findAll();

    Optional<User> find(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);



}


package ru.itis.blog.repositories.jdbc;

import ru.itis.blog.models.User;

import java.util.Optional;

public interface UsersRepository extends CRUDRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmCode(String token);

}



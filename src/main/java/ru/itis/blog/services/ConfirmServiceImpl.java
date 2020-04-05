package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.State;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jdbc.UsersRepository;

import java.util.Optional;

@Component
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void confirm(String token) {
        Optional<User> userOptional = usersRepository.findByConfirmCode(token);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setState(State.CONFIRMED);
            usersRepository.update(user);

        }
    }
}

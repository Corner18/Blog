package ru.itis.blog.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.blog.dto.LoginDto;
import ru.itis.blog.dto.TokenDto;
import ru.itis.blog.models.State;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jdbc.UsersRepository;


import java.util.Optional;

@Service
public class LoginTokenServiceImpl implements LoginTokenService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDto login(LoginDto loginDto) {
        Optional<User> userOptional = usersRepository.findByEmail(loginDto.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if ((passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) &&
                    (user.getState().equals(State.CONFIRMED))){
                String token = Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim("name", user.getName())
                        .claim("role", user.getRole().name())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return new TokenDto(token);
            } else throw new AccessDeniedException("Wrong email/password");
        } else throw new AccessDeniedException("User not found");
    }
}

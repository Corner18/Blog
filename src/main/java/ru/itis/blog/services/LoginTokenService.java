package ru.itis.blog.services;


import ru.itis.blog.dto.LoginDto;
import ru.itis.blog.dto.TokenDto;

public interface LoginTokenService {
    TokenDto login(LoginDto loginDto);
}

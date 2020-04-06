package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.dto.LoginDto;
import ru.itis.blog.dto.TokenDto;
import ru.itis.blog.services.LoginTokenService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginTokenService loginService;

    @GetMapping
    public ModelAndView getLoginPage(Authentication authentication){
        if (authentication != null) {
            return new ModelAndView("redirect:/profile");
        }
        return new ModelAndView("login");
    }

    @PostMapping
    public ModelAndView login(HttpServletResponse response, LoginDto loginDto) throws IOException {
        TokenDto tokenDto = loginService.login(loginDto);
        Cookie cookie = new Cookie("MyToken", tokenDto.getToken());
        response.addCookie(cookie);
        return new ModelAndView("redirect:/profile");
    }


}

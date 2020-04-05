package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.dto.PostDto;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.AddPostService;

@Controller
@RequestMapping("/add")
public class AddingPostController {
    @Autowired
    AddPostService addPostService;

    @GetMapping
    private ModelAndView getAddingPage(Authentication authentication) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return new ModelAndView("add", "user", userDetails.getUser());
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping
    private ModelAndView addPost(Authentication authentication, PostDto postDto) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            addPostService.save(postDto, userDetails.getUserId());
            return new ModelAndView("redirect:/main");
        }
        return new ModelAndView("redirect:/login");
    }
}

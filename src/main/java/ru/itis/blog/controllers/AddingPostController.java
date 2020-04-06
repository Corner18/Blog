package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.dto.PostDto;
import ru.itis.blog.models.User;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.AddPostService;
import ru.itis.blog.services.UsersService;

@Controller
public class AddingPostController {

    @Autowired
    private AddPostService addPostService;

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/add")
    public ModelAndView getAddingPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = usersService.getUser(userDetails.getUserId());
        return new ModelAndView("add", "user", user);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public ModelAndView addPost(PostDto postDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        addPostService.save(postDto, userDetails.getUserId());
        return new ModelAndView("redirect:/main");
    }
}

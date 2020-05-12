package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.models.User;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.CommentService;
import ru.itis.blog.services.FileStorageService;
import ru.itis.blog.services.PostService;
import ru.itis.blog.services.UsersService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/{post_id}")
    public String getPostPage(@PathVariable("post_id") Long post_id, Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("comments", commentService.userComment(post_id));
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("post", postService.getOne(post_id));
        return "post";

    }

}

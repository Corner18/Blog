package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.FileStorageService;
import ru.itis.blog.services.PostService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/{post_id}")
    public ModelAndView getPostPage(Authentication authentication, @PathVariable("post_id") Long post_id, HttpServletRequest request) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            HttpSession session = request.getSession();
            session.setAttribute("post_id", post_id);
            Map<String, Object> params = new HashMap<>();
            params.put("comments", postService.userComment(post_id));
            params.put("user", userDetails.getUser());
            params.put("post", postService.getOne(post_id));
            return new ModelAndView("post");
        } else {
            return new ModelAndView("redirect:/login");
        }

    }

    @PostMapping("/post")
    public ModelAndView makeComment(Authentication authentication, CommentDto form, HttpServletRequest request) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            form.setUser_id(userDetails.getUser().getId());
            form.setPost_id(Long.parseLong(String.valueOf(request.getSession().getAttribute("post_id"))));
            postService.makeComment(form);
        }
        String id = String.valueOf(request.getSession().getAttribute("post_id"));
        return new ModelAndView("redirect:/post/" + id);
    }

    @PostMapping("/like")
    public ModelAndView makeLike(Authentication authentication, HttpServletRequest request, LikeDto form) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            form.setPost_id(Long.parseLong(String.valueOf(request.getSession().getAttribute("post_id"))));
            form.setUser_id(userDetails.getUser().getId());
            postService.makeLike(form);
        }
        String id = String.valueOf(request.getSession().getAttribute("post_id"));
        return new ModelAndView("redirect:/post/" + id);
    }

}

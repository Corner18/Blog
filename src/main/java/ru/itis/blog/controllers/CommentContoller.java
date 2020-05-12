package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.CommentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentContoller {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/{post_id}")
    public String makeComment(CommentDto form, @RequestParam("post_id") Long postId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        form.setUser_id(userDetails.getUser().getId());
        form.setPost_id(postId);
        commentService.makeComment(form);
        return "redirect:/post/";
    }
}

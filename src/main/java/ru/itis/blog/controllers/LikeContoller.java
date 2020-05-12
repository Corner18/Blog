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
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.LikeService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LikeContoller {

    @Autowired
    private LikeService likeService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/like/{post_id}")
    public String makeLike(@RequestParam("post_id") Long postId, Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LikeDto form = LikeDto.builder()
                .post_id(postId)
                .user_id(userDetails.getUser().getId())
                .build();
        likeService.makeLike(form);
        return "redirect:/post/" + postId;
    }
}

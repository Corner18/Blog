package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
    public ModelAndView makeLike(@RequestParam("post_id") Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        LikeDto form = LikeDto.builder()
                .post_id(postId)
                .user_id(userDetails.getUserId())
                .build();
        likeService.makeLike(form);
        return new ModelAndView("redirect:/post/" + postId);
    }
}

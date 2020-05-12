package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.LikeService;

@RestController
public class LikeRestContoller {

    @Autowired
    private LikeService likeService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/like/{post_id}")
    public ResponseEntity<?> makeLike(@PathVariable("post_id") Long post_id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LikeDto likeDto = LikeDto.builder()
                .post_id(post_id)
                .user_id(userDetails.getUser().getId())
                .build();
        likeService.makeLike(likeDto);
        return ResponseEntity.accepted().build();
    }
}

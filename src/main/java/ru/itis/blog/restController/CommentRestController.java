package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.CommentService;

@RestController
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/comment/{post_id}")
    public ResponseEntity<?> makeComment(@RequestParam(value = "text") String text, @PathVariable("post_id") Long post_id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        CommentDto commentDto = CommentDto.builder()
                .comment(text)
                .post_id(post_id)
                .user_id(userDetails.getUser().getId())
                .build();
        commentService.makeComment(commentDto);
        return ResponseEntity.accepted().build();
    }
}

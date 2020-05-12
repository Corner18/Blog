package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.blog.dto.PostDto;
import ru.itis.blog.models.User;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.AddPostService;
import ru.itis.blog.services.UsersService;

@RestController
@RequestMapping("/api/add")
public class AddingPostRestController {

    @Autowired
    private AddPostService addPostService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody PostDto postDto, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        addPostService.save(postDto, userDetails.getUser().getId());
        return ResponseEntity.accepted().build();
    }
}

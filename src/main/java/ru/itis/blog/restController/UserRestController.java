package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.itis.blog.models.User;

import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.FileStorageService;
import ru.itis.blog.services.UsersService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/profile")
public class UserRestController {

    @Autowired
    UsersService usersService;

    @Autowired
    FileStorageService fileStorageService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<User> getSelf(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ResponseEntity<>(userDetails.getUser(), HttpStatus.OK);
    }

}

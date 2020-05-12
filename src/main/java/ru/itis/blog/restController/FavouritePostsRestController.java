package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.blog.models.Post;
import ru.itis.blog.models.User;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.FavouritesService;
import ru.itis.blog.services.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouritePostsRestController {

    @Autowired
    private FavouritesService favouritesService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Post>> getFavouritePosts(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ResponseEntity<>(favouritesService.favs(userDetails.getUser().getId()), HttpStatus.OK);
    }
}

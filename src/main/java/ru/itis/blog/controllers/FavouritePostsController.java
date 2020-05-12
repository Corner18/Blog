package ru.itis.blog.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.models.Post;
import ru.itis.blog.models.User;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.FavouritesService;
import ru.itis.blog.services.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FavouritePostsController {

    @Autowired
    private FavouritesService favouritesService;

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/favourites")
    public String getFavourites(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("posts", favouritesService.favs(userDetails.getUser().getId()));
        model.addAttribute("user", userDetails.getUser());
        return "favourite_posts";
    }


}

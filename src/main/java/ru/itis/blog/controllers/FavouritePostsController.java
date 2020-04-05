package ru.itis.blog.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.models.Post;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.FavouritesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FavouritePostsController {

    @Autowired
    private FavouritesService favouritesService;

    @GetMapping("/favourites")
    public ModelAndView getFavourites(Authentication authentication, Model model){
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<Post> postList = favouritesService.favs(userDetails.getUser().getId());
            Map<String, Object> params = new HashMap<>();
            params.put("posts", postList);
            params.put("user", userDetails.getUser());
            return new ModelAndView("favourite_posts",params);
        }
        return new ModelAndView("redirect:/login");
    }
}

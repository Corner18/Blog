package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.models.Post;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.MainService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/main")
    public ModelAndView mainPage(Authentication authentication) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<Post> postList = mainService.getAll();
            Map<String, Object> params = new HashMap<>();
            params.put("posts", postList);
            params.put("user", userDetails.getUser());
            return new ModelAndView("posts");
        } else {
            return new ModelAndView("redirect:/login");
        }

    }
}

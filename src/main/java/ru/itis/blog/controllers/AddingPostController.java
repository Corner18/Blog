package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.dto.PostDto;
import ru.itis.blog.models.User;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.AddPostService;
import ru.itis.blog.services.UsersService;

@Controller
@RequestMapping("/add")
public class AddingPostController {

    @Autowired
    private AddPostService addPostService;

    public String getAddingPage(Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        return "add";
    }

    public String addPost(PostDto postDto, Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        addPostService.save(postDto, userDetails.getUser().getId());
        return "redirect:/main";
    }
}

package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.dto.RegistrationDto;

import ru.itis.blog.services.RegistrationService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
        public ModelAndView getRegistrationPage(Authentication authentication){
        if (authentication != null) {
            return new ModelAndView("redirect:/profile");
        }
        return new ModelAndView("registration");
    }

    @PostMapping
    public ModelAndView registration(RegistrationDto form) {
        registrationService.registration(form);
        return new ModelAndView("redirect:/login");
    }

}

package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import ru.itis.blog.dto.RegistrationDto;
import ru.itis.blog.services.RegistrationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public String reg(Authentication authentication, Model model) {
        if(authentication != null) {
            return "redirect:/profile";
        }else{
            model.addAttribute("r", new RegistrationDto());
            return "registration";
        }
    }

    @PostMapping
    public String reg(@Valid @ModelAttribute("r")RegistrationDto form, BindingResult bindingResult, Model model) {
        System.out.println(form);
        System.out.println(bindingResult.getAllErrors());
        if(bindingResult.hasErrors()) {
            model.addAttribute("r", form);
            return "registration";
        }else {
            registrationService.registration(form);
            return "login";
        }
    }
}

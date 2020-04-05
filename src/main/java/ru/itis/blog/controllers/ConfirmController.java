package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.services.ConfirmService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/confirm/{token}")
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping
    public ModelAndView getConfirmPage(@PathVariable("token") String token, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        if (token != null) {
            confirmService.confirm(token);
            response.sendRedirect("/login");
        }
        modelAndView.setViewName("registration");
        return modelAndView;
    }
}




package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jdbc.UsersRepository;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.FileStorageService;
import ru.itis.blog.services.UsersService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class StorageController {

    @Autowired
    private FileStorageService service;

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/storage")
    public ModelAndView getStoragePage() {
        return new ModelAndView("file_upload");
    }


    @GetMapping("/files")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        service.saveFile(file, userDetails.getUser());
        return new ModelAndView("redirect:/profile");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        service.writeFileToResponse(fileName, response);
    }
}

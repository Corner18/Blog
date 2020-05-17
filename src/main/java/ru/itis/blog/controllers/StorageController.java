package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.FileStorageService;
import ru.itis.blog.services.UsersService;


import javax.servlet.http.HttpServletResponse;


@Controller
public class StorageController {

    @Autowired
    private FileStorageService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/storage")
    public String getStoragePage(Authentication authentication) {
        return "file_upload";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Authentication authentication) {
        System.out.println("я пытаюсь загурзить файл");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        service.saveFile(file, userDetails.getUser());
        return "redirect:/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        service.writeFileToResponse(fileName, response);
    }
}

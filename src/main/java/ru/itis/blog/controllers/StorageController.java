package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
    private UsersRepository usersRepository;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/storage", method = RequestMethod.GET)
    public ModelAndView getStoragePage() {
        return new ModelAndView("file_upload");
    }


    @RequestMapping(value = "/files", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) throws IOException {
        service.saveFile(file, usersRepository.find(userDetails.getUser().getId()).get());
        return new ModelAndView("redirect:/profile");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/files/{file-name:.+}", method = RequestMethod.GET)
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        service.writeFileToResponse(fileName, response);
    }
}

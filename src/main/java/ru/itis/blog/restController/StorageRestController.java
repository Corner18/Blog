package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.blog.dto.FileInfoDto;
import ru.itis.blog.models.User;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.FileStorageService;
import ru.itis.blog.services.UsersService;


@RequestMapping("/api/files")
@RestController
public class StorageRestController {

    @Autowired
    private FileStorageService service;

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = usersService.getUser(userDetails.getUserId());
        service.saveFile(file, user);
        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{file-name:.+}")
    public ResponseEntity<FileInfoDto> getFileInfoDto(@PathVariable("file-name") String fileName) {
        FileInfoDto fileInfoDto = service.getFileInfo(fileName);
        return new ResponseEntity<>(fileInfoDto, HttpStatus.OK);
    }
}

package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.blog.dto.RegistrationDto;
import ru.itis.blog.services.RegistrationService;


@RestController
@RequestMapping("/api/registration")
public class RegistrationRestController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody RegistrationDto form) {
        registrationService.registration(form);
        return ResponseEntity.accepted().build();
    }
}


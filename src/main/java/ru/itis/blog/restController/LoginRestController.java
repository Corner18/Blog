package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.blog.dto.LoginDto;
import ru.itis.blog.dto.TokenDto;
import ru.itis.blog.services.LoginTokenService;


@RestController
@RequestMapping("/api/login")
public class LoginRestController {

    @Autowired
    private LoginTokenService loginTokenService;

    @PostMapping
    public ResponseEntity<TokenDto> signIn(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(loginTokenService.login(loginDto));
    }

}

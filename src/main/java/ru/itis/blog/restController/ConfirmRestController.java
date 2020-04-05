package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.blog.services.ConfirmService;


@RestController
@RequestMapping("/api/confirm")
public class ConfirmRestController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/{token}")
    public ResponseEntity<?> confirm(@PathVariable("token") String token) {
        if( token!= null) {
            confirmService.confirm(token);
            return ResponseEntity.accepted().build();
        }
        return null;
    }
}

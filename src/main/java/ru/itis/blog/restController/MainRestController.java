package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.blog.models.Post;
import ru.itis.blog.services.MainService;

import java.util.List;

@RestController
@RequestMapping("/api/main")
public class MainRestController {

    @Autowired
    private MainService mainService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(){
        return new ResponseEntity<>(mainService.getAll(), HttpStatus.OK);
    }
}

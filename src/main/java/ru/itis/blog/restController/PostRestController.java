package ru.itis.blog.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.models.Post;
import ru.itis.blog.models.User;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.CommentService;
import ru.itis.blog.services.PostService;
import ru.itis.blog.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostRestController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/post/{post_id}")
    public ResponseEntity<Map<Post, List<CommentDto>>> getPostPage(@PathVariable("post_id") Long post_id) {
        Post post = postService.getOne(post_id);
        List<CommentDto> comments = commentService.getComments(post_id);
        Map<Post,List<CommentDto>> map = new HashMap<>();
        map.put(post,comments);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }



}

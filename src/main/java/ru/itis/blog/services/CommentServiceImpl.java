package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.dto.CommentFreemarkerDto;
import ru.itis.blog.models.Comment;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jpa.UsersRepository;
import ru.itis.blog.repositories.jpa.CommentRepository;
import ru.itis.blog.repositories.jpa.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<CommentFreemarkerDto> userComment(Long post_id) {
        List<CommentFreemarkerDto> commentList = new ArrayList<>();
        CommentFreemarkerDto commentFreemarkerDto = new CommentFreemarkerDto();
        List<Comment> comments = commentRepository.getAllByPost_Id(post_id);
        for (Comment comment : comments) {
            Optional<User> userOptional = usersRepository.find(comment.getUser_id());
            userOptional.ifPresent(user -> commentList.add(commentFreemarkerDto.from(comment, user)));
        }
        return commentList;
    }

    @Override
    public void makeComment(CommentDto form) {
        Comment comment = Comment.builder()
                .post(postRepository.findById(form.getPost_id()))
                .user_id(form.getUser_id())
                .text(form.getComment())
                .build();
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getComments(Long post_id) {
        return CommentDto.from(commentRepository.getAllByPost_Id(post_id));
    }
}

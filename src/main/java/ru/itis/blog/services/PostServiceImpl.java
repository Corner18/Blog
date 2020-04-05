package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.blog.dto.CommentDto;
import ru.itis.blog.dto.CommentFreemarkerDto;
import ru.itis.blog.dto.LikeDto;
import ru.itis.blog.models.Comment;
import ru.itis.blog.models.Likes;
import ru.itis.blog.models.Post;
import ru.itis.blog.models.User;
import ru.itis.blog.repositories.jdbc.UsersRepository;
import ru.itis.blog.repositories.jpa.CommentRepository;
import ru.itis.blog.repositories.jpa.LikeRepository;
import ru.itis.blog.repositories.jpa.PostRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LikeRepository likeRepository;


    @Override
    public List<CommentFreemarkerDto> userComment(Long post_id) {
        List<CommentFreemarkerDto> commentList = new ArrayList<>();
        CommentFreemarkerDto commentFreemarkerDto = new CommentFreemarkerDto();
        List<Comment> comments = commentRepository.getAllByPost_Id(post_id);
        for (Comment comment : comments){
            Optional<User> userOptional = usersRepository.find(comment.getUser_id());
            userOptional.ifPresent(user -> commentList.add(commentFreemarkerDto.from(comment, user)));
        }
        return commentList;
    }

    @Override
    public Post getOne(Long id) {
        Post post = postRepository.findById(id);
        return post;
    }

    @Override
    public void makeComment(CommentDto form) {
        Comment comment = Comment.builder()
                .post(postRepository.findById(form.getPost_id()))
                .user_id(usersRepository.find(form.getUser_id()).get().getId())
                .text(form.getComment())
                .build();
        commentRepository.save(comment);
    }

    @Override
    public void makeLike(LikeDto form) {
        Likes like = Likes.builder()
                .post(postRepository.findById(form.getPost_id()))
                .user_id(usersRepository.find(form.getUser_id()).get().getId())
                .build();
        likeRepository.save(like);
    }

    @Override
    public List<CommentDto> getComments(Long post_id) {
        return CommentDto.from(commentRepository.getAllByPost_Id(post_id));
    }
}

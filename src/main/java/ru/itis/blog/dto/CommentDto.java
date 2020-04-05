package ru.itis.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long user_id;
    private Long post_id;
    private String comment;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .user_id(comment.getUser_id())
                .post_id(comment.getPost().getId())
                .comment(comment.getText())
                .build();
    }

    public static List<CommentDto> from(List<Comment> comments) {
        return comments.stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }
}

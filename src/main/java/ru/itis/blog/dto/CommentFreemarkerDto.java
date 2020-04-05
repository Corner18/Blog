package ru.itis.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Comment;
import ru.itis.blog.models.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentFreemarkerDto {
    private String avatar;
    private String name;
    private String text;

    public CommentFreemarkerDto from(Comment comment, User user) {
        return CommentFreemarkerDto.builder()
                .avatar(user.getAvatar())
                .name(user.getName())
                .text(comment.getText())
                .build();
    }

}

package ru.itis.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Comment;
import ru.itis.blog.models.Message;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {

    private String text;
    private String sender;
    private String receiver;
    private String pageId;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .receiver(message.getReceiver_email())
                .sender(message.getSender_email())
                .text(message.getText())
                .pageId(message.getPage_id())
                .build();
    }

    public static List<MessageDto> from(List<Message> messages) {
        return messages.stream()
                .map(MessageDto::from)
                .collect(Collectors.toList());
    }
}
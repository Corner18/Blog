package ru.itis.blog.models;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()

public class Message {

    private Long id;
    private String text;

    private String sender_email;

    private String receiver_email;

    private String page_id;

}
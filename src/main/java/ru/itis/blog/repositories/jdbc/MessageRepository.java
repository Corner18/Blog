package ru.itis.blog.repositories.jdbc;

import ru.itis.blog.models.Message;

import java.util.List;

public interface MessageRepository extends CRUDRepository<Message, Long> {
    List<Message> getAllByReceiver_Email(String email);

    List<Message> getAllBySender_Email(String email);

    List<Message> getAllBySender_EmailAndReceiver_Email(String senderEmail, String receiverEmail);

    List<Message> distinct();

}

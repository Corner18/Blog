package ru.itis.blog.services;

import ru.itis.blog.dto.MessageDto;
import ru.itis.blog.models.Message;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MessageService {
    void save(MessageDto messageDto);
    List<MessageDto> getDialogue(String email1, String email2);
    Set<String> getEmailsForAdminPage(String adminEmail);
    List<MessageDto> getBySenderAndReceiver(String sender, String receiver);
    Map<String, List<MessageDto>> map();
}

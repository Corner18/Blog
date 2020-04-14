package ru.itis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.blog.dto.MessageDto;
import ru.itis.blog.models.Message;
import ru.itis.blog.repositories.jdbc.MessageRepository;
import ru.itis.blog.repositories.jdbc.UsersRepository;

import java.util.*;

@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public void save(MessageDto messageDto) {

        Message message = Message.builder()
                .receiver_email(messageDto.getReceiver())
                .sender_email(messageDto.getSender())
                .text(messageDto.getText())
                .page_id(messageDto.getPageId())
                .build();
        messageRepository.save(message);
    }

    @Override
    public List<MessageDto> getDialogue(String email1, String email2) {
        List<Message> dialogue = messageRepository.getAllBySender_EmailAndReceiver_Email(email1, email2);
        dialogue.addAll(messageRepository.getAllBySender_EmailAndReceiver_Email(email2, email1));
        dialogue.sort(Comparator.comparingLong(Message::getId));
        return MessageDto.from(dialogue);
    }

    @Override
    public Set<String> getEmailsForAdminPage(String adminEmail) {
        List<Message> messages = messageRepository.getAllByReceiver_Email(adminEmail);
        Set<String> emails = new HashSet<>();
        for (Message message : messages) {
            emails.add(message.getSender_email());
        }
        return emails;
    }

    @Override
    public List<MessageDto> getBySenderAndReceiver(String sender, String receiver) {
        return MessageDto.from(messageRepository.getAllBySender_EmailAndReceiver_Email(sender, receiver));
    }

    @Override
    public Map<String, List<MessageDto>> map() {
        List<Message> messages = messageRepository.distinct();
        Map<String, List<MessageDto>> map = new HashMap<>();
        for (Message message : messages){
            List<MessageDto> messageDtos = new ArrayList<>();
            messageDtos.add(MessageDto.from(message));
            map.put(message.getPage_id(),messageDtos );
        }
        return map;
    }
}

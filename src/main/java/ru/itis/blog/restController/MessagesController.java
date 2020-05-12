package ru.itis.blog.restController;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.blog.dto.MessageDto;
import ru.itis.blog.models.Message;
import ru.itis.blog.services.MessageService;

import java.util.*;

@RestController
public class MessagesController {

    @Autowired
    private MessageService messageService;

    private static final Map<String, List<MessageDto>> messages = new HashMap<>();

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {

        if (!messages.containsKey(message.getPageId())) {
            messages.put(message.getPageId(), new ArrayList<>());
        }

        messageService.save(message);
        for (String key : messages.keySet()) {
            if ((messageService.getBySenderAndReceiver(message.getReceiver(), message.getSender()).get(0).getPageId().equals(key)) ||
                    (messageService.getBySenderAndReceiver(message.getSender(), message.getReceiver()).get(0).getPageId().equals(key)))
                synchronized (messages.get(key)) {
                    messages.get(key).add(message);
                    messages.get(key).notifyAll();
                }
        }
        return ResponseEntity.ok().build();
    }


    @SneakyThrows
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("pageId") String pageId) {
        synchronized (messages.get(pageId)) {
            if (messages.get(pageId).isEmpty()) {
                messages.get(pageId).wait();
            }
        }
        List<MessageDto> response = new ArrayList<>(messages.get(pageId));
        messages.get(pageId).clear();
        return ResponseEntity.ok(response);
    }
}
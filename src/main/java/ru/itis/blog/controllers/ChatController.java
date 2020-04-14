package ru.itis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.blog.dto.MessageDto;
import ru.itis.blog.models.Role;
import ru.itis.blog.models.User;
import ru.itis.blog.security.details.UserDetailsImpl;
import ru.itis.blog.services.MessageService;
import ru.itis.blog.services.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ChatController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private MessageService messageService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/support/{receiver:.+}")
    public ModelAndView getChatPage(@PathVariable("receiver") String receiver) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = usersService.getUser(userDetails.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("admin", user.getEmail());
        map.put("receiver", receiver);
        map.put("messages", messageService.getDialogue(receiver, user.getEmail()));
        List<MessageDto> messageDtos = messageService.getBySenderAndReceiver(user.getEmail(),receiver);
        if(messageDtos.isEmpty()){
            map.put("pageId",UUID.randomUUID().toString());
        } else{
            map.put("pageId",messageDtos.get(0).getPageId());
        }
        map.put("sender", user.getEmail());

        return new ModelAndView("support", map);
    }

    @GetMapping("/support")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getChat() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = usersService.getUser(userDetails.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("admin", usersService.getAdmin().getEmail());
        map.put("messages", messageService.getDialogue(user.getEmail(), usersService.getAdmin().getEmail()));
        map.put("receiver", usersService.getAdmin().getEmail());
        List<MessageDto> messageDtos = messageService.getBySenderAndReceiver(user.getEmail(),usersService.getAdmin().getEmail());
       if(messageDtos.isEmpty()){
            map.put("pageId",UUID.randomUUID().toString());
       } else{
           map.put("pageId",messageDtos.get(0).getPageId());
       }
        map.put("sender", user.getEmail() );
        return new ModelAndView("support", map);

    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView getDialoguesList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = usersService.getUser(userDetails.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("dialogues", messageService.getEmailsForAdminPage(user.getEmail()));

        return new ModelAndView("dialogues", map);
    }

}
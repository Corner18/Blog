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
    public String getChatPage(@PathVariable("receiver") String receiver, Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("admin", userDetails.getUsername());
        model.addAttribute("receiver", receiver);
        model.addAttribute("messages",messageService.getDialogue(receiver, userDetails.getUsername()));
        List<MessageDto> messageDtos = messageService.getBySenderAndReceiver(userDetails.getUsername(), receiver);
        if (messageDtos.isEmpty()) {
            model.addAttribute("pageId", UUID.randomUUID().toString());
        } else {
            model.addAttribute("pageId", messageDtos.get(0).getPageId());
        }
        model.addAttribute("sender", userDetails.getUsername());
        return "support";
    }

    @GetMapping("/support")
    @PreAuthorize("isAuthenticated()")
    public String getChat(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("admin", usersService.getAdmin().getEmail());
        model.addAttribute("messages", messageService.getDialogue(userDetails.getUsername(), usersService.getAdmin().getEmail()));
        model.addAttribute("receiver", usersService.getAdmin().getEmail());
        List<MessageDto> messageDtos = messageService.getBySenderAndReceiver(userDetails.getUsername(), usersService.getAdmin().getEmail());
        if (messageDtos.isEmpty()) {
            model.addAttribute("pageId", UUID.randomUUID().toString());
        } else {
            model.addAttribute("pageId", messageDtos.get(0).getPageId());
        }
        model.addAttribute("sender", userDetails.getUsername());
        return "support";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getDialoguesList(Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("dialogues", messageService.getEmailsForAdminPage(userDetails.getUsername()));
        return "dialogues";
    }

}
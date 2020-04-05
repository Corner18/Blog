package ru.itis.blog.services;


import ru.itis.blog.models.User;

public interface SMSService {
    void sendSms(User user, String text);
}

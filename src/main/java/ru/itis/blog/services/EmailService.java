package ru.itis.blog.services;

public interface EmailService {
    void sendMail(String subject, String text, String email);
}

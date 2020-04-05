package ru.itis.blog.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmailSending {

    @Autowired
    private FileUploadEmail fileUploadEmail;

    @AfterReturning(pointcut = "execution(* ru.itis.blog.services.FileStorageService.saveFile(..))", returning = "storageFileName")
    public void logAfterReturning(String storageFileName) {
        fileUploadEmail.sendEmail(storageFileName);
        System.out.println();
    }
}

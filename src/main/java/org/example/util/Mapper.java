package org.example.util;

import org.example.data.model.User;
import org.example.dto.EmailRequest;
import org.example.dto.request.RegisterRequest;

public class Mapper {
    public static boolean verifyEmail(String email){
        return email.matches("[a-zA-Z\\d+/_@.-]+@[a-z]+[/.][a-z]{2,3}");
    }
    public static boolean verifyPassword(String password){
        return password.matches("[A-Z][a-zA-Z]{4,}[0-9/@-_.?:^&!(){}#*%$]{1,}");
    }
    public static User map(RegisterRequest registerRequest){
        User user = new User();
        if(registerRequest.getFirstName() != null)user.setFirstName(registerRequest.getFirstName());
        if(registerRequest.getLastName() != null)user.setLastName(registerRequest.getLastName());
        if(registerRequest.getEmail() != null)user.setEmail(registerRequest.getEmail());
        if(registerRequest.getPassword() != null) {
            String password = HashPassword.hashPassword(registerRequest.getPassword());
            user.setPassword(password);
        }
        return user;
    }


    public static EmailRequest mapEmail(String email,String messageBody,String subject) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setReciepent(email);
        emailRequest.setMessage("""
                   Welcome to wordPress.com""" + "\n" + messageBody);
        emailRequest.setTitle(subject);
        return emailRequest;
    }


}

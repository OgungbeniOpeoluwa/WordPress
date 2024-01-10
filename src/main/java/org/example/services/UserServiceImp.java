package org.example.services;

import org.example.data.model.User;
import org.example.data.model.UserRole;
import org.example.data.repository.UserRepository;
import org.example.dto.EmailRequest;
import org.example.dto.WriteRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exception.InvalidDetailsFormat;
import org.example.exception.InvalidLoginDetails;
import org.example.exception.UserExistException;
import org.example.services.email.EmailService;
import org.example.util.HashPassword;
import org.example.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.util.Mapper.*;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    SiteService  siteService;
    @Autowired
    EmailService emailService;
    @Override
    public void register(RegisterRequest register) {
        if(userExist(register.getEmail()))throw new UserExistException("Email already exist");
        if(!verifyEmail(register.getEmail()))throw new InvalidDetailsFormat("wrong email format");
        if(!verifyPassword(register.getPassword()))throw new InvalidDetailsFormat("Weak password");
        User user = map(register);
        EmailRequest request = Mapper.mapEmail(register.getEmail(),
                "you have successfully created an account with " + register.getDomainName(),
                        "WordPress.com" );
        emailService.send(request);
        siteService.createSite(user.getId(),register.getDomainName());
        userRepository.save(user);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        if(!userExist(loginRequest.getEmail()))throw new InvalidLoginDetails("user doesn't exist");
        verifyLoginPassword(loginRequest);
        siteService.unlockSite(loginRequest.getDomainName());
    }

    @Override
    public void write(WriteRequest request) {
        if(!userExist(request.getEmail()))throw new UserExistException("user doesn't exist");
        User user = userRepository.findByEmail(request.getEmail());
        String fullname = user.getFirstName() + " " + user.getLastName();
        siteService.write(user.getRole(),user.getId(),fullname,request);

    }


    private boolean userExist(String email){
        User user = userRepository.findByEmail(email);
        return user!=null;
    }
    private void verifyLoginPassword(LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail());
        String salt = HashPassword.getExitedPasswordSaltValue(user.getPassword());
        String oldPassword = HashPassword.clearSaltValueInPassword(user.getPassword());
        String givenPassword = HashPassword.securePassword(loginRequest.getPassword(),salt);
        if(!oldPassword.equals(givenPassword))throw new InvalidLoginDetails("Invalid details");

    }
}

package org.example.services;

import org.example.data.repository.SiteRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.WriteRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exception.InvalidDetailsFormat;
import org.example.exception.InvalidLoginDetails;
import org.example.exception.SiteExistException;
import org.example.exception.UserExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceImpTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SiteRepository siteRepository;

    private  RegisterRequest register;
    @AfterEach
    public void doThisAfter(){
        userRepository.deleteAll();
        siteRepository.deleteAll();
    }
    @BeforeEach
    public void register(){
        register = new RegisterRequest();
        register.setFirstName("opeoluwa");
        register.setLastName("martins");
        register.setEmail("ogungbeniopemipo1@gmail.com");
        register.setPassword("Opemip@1");
        register.setDomainName("ThinkWithMe");

    }


    @Test
    public void testThatWhenUserRegisterWithWrongEmailFormatThrowsAnException(){
        register.setEmail("opeoluwadhhdhhs.com");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(register));
    }
    @Test
    public void testThatUserRegisterWithWrongPasswordFormatThrowsAnException(){
        register.setPassword("1234");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(register));
    }
    @Test
    public void testThatUserCanNotRegisterWithTheSameEmailAccount(){
        userService.register(register);
        assertThrows(UserExistException.class,()->userService.register(register));

    }
    @Test
    public void testThatIfUserLoginWithWrongPasswordThrowsAnException(){
        userService.register(register);
        LoginRequest loginRequest = new LoginRequest("opeoluwa@gmail.com","Opemip","ThinkWithMe");
        assertThrows(InvalidLoginDetails.class,()->userService.login(loginRequest));
    }
    @Test
    public void testThatIfUserLoginWithWrongEmailThrowsException(){
        userService.register(register);
        LoginRequest loginRequest = new LoginRequest("agnes@gmail.com","Opemip@1","ThinkWithMe");
        assertThrows(InvalidLoginDetails.class,()->userService.login(loginRequest));
    }
    @Test
    public void testThatIfUserDomainNameDoesntExistUserCantLogin(){
        userService.register(register);
        LoginRequest loginRequest = new LoginRequest("ogungbeniopemipo1@gmail.com","Opemip@1","risen");
        assertThrows(SiteExistException.class,()->userService.login(loginRequest));
    }

    @Test
    public void testThatIfUserEmailIsNotFoundItThrowsAnException(){
        WriteRequest request = new WriteRequest("opemipo@gmail.com","ThinkwithTobi","Bad Request","sending bad reequest");
        assertThrows(UserExistException.class,()->userService.write(request));
    }
    @Test
    public void testThatIfUserDomainNameDoesntExistThrowsAnException(){
        userService.register(register);
        LoginRequest loginRequest = new LoginRequest("ogungbeniopemipo1@gmail.com","Opemip@1","ThinkWithMe");
        userService.login(loginRequest);
        WriteRequest request = new WriteRequest("ogungbeniopemip@1@gmail.com","Risen","Bad request","request");
        assertThrows(SiteExistException.class,()->userService.write(request));
    }



}
package com.example.five_puzzle_api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.five_puzzle_api.models.User;
import com.example.five_puzzle_api.security.JWTService;
import com.example.five_puzzle_api.services.UserService;

@SpringBootTest
class UserServiceTest {

    private Data dataMock;
    private JWTService jwtUtilMock;
    private UserService userService;
	
    @BeforeEach
    public void setup(){
        dataMock = new Data();
        jwtUtilMock = new JWTService();
        userService = new UserService(dataMock, jwtUtilMock);
        String username = "testUser";
        String password = "testPassword";
        dataMock.addUsers(new User(username, password, "1"));
    }
    
    @Test
	void testLoginSuccess() throws Exception {
        String username = "testUser2";
        String password = "testPassword2";
        dataMock.addUsers(new User(username, password, "2"));
        
        String token = userService.login(username, password);
        
        assertNotNull(token);
        Boolean success = jwtUtilMock.validateToken(token);
        assert(success == true);
    }
    
    @Test
    void testLoginFailure() {
        String username = "nonExistentUser";
        String password = "wrongPassword";
        
        Exception exception = null;
        try {
            userService.login(username, password);
        } catch (Exception e) {
            exception = e;
        }
        
        assertNotNull(exception);
        assert(exception.getMessage().equals("User not found"));
    }

    @Test
    void testLoginFailure2() {
        String username = "testUser";
        String password = "wrongPassword";
        
        Exception exception = null;
        try {
            userService.login(username, password);
        } catch (Exception e) {
            exception = e;
        }
        
        assertNotNull(exception);
        assert(exception.getMessage().equals("User not found"));
    }
}

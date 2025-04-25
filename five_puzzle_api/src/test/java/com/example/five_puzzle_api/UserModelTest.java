package com.example.five_puzzle_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.five_puzzle_api.models.User;

@SpringBootTest
public class UserModelTest {


    @Test
    public void testUser() {
        
        int userTestCount = 1000;

        for (int i = 0; i < userTestCount; i++) {
            User user = new User("testUser" + i, "testPassword" + i, String.valueOf(i));
            assert user.getId().equals(String.valueOf(i));
            assert user.getUsername().equals("testUser" + i);
            assert user.getPassword().equals("testPassword" + i);
            assert user.getUserType().toString().equals("USER");
        }        
    }

}

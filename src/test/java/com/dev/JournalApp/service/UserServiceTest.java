package com.dev.JournalApp.service;

import com.dev.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;


    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "sham",
            "vipul"
    })
    public void testfindByUserName(String name) {
        assertNotNull(userRepository.findByUserName(name), "failed for " + name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,3,5",
            "3,4,7"
    })
    public void test(int a, int b, int c) {
        assertEquals(c, a + b);
    }

}

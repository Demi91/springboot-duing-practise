package com.duing;

import com.duing.handler.MailHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DuingPractiseApplicationTests {

    @Autowired
    private MailHandler mailHandler;

    @Test
    void contextLoads() {
//        mailHandler.send();
        try {
            mailHandler.sendByTemplate();
        } catch (Exception e) {
        }
    }

}

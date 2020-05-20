package com.duing.controller;

import com.duing.handler.MailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailHandler mailHandler;

    @GetMapping("/async")
    public String async() {
        try {
            mailHandler.sendByTemplate();
        }catch (Exception e){}
        return "success";
    }
}

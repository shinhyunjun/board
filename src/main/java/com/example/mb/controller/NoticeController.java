package com.example.mb.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@RequestMapping("/notice")
@Controller
public class NoticeController {

    @RequestMapping("/list")
    public void list(){
        log.info("access to all");
    }

    @RequestMapping("/register")
    public void registerForm(){
        log.info("registerForm: access to admin");
    }
}

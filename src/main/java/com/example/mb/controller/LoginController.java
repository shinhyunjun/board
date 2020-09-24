package com.example.mb.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
public class LoginController {

    @RequestMapping("/login")

    //에러 메시지와 로그아웃 메시지를 파라미터로 사용한다.
    public String loginForm(String error, String logout, Model model){

        log.info("error:" + error);
        log.info("logout:" + logout);

        if(error != null){
            model.addAttribute("error","Login Error!!!");

        }

        if(logout != null){
            model.addAttribute("logout","Logout!!!");

        }

        return "loginForm";
    }

    @RequestMapping("/logout")
    public String logoutForm() {
        log.info("logoutForm");

        return "logoutForm";
    }
}

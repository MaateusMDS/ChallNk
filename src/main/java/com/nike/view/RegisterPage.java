package com.nike.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegisterPage {

    @GetMapping("/register")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("register");
        return mv;
    }

}
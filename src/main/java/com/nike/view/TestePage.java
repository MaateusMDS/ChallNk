package com.nike.view;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/teste")
public class TestePage {

    @GetMapping()
    public ModelAndView page(Model model, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("chat");
        return mv;
    }
}

package com.nike.view;

import com.nike.dominio.usuario.model.Usuario;
import com.nike.dominio.usuario.repository.RepositoryUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomePage {

    @Autowired
    private RepositoryUser repository;

    @GetMapping()
    public ModelAndView page(Model model, HttpServletRequest request) {
        List<Usuario> usuarios = repository.findAll();
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("currentUrl", request.getRequestURI());
        return mv;
    }
}
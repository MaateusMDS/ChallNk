package com.nike.view;

import com.nike.dominio.categoria.repository.RepositoryCategoria;
import com.nike.dominio.produto.model.Produto;
import com.nike.dominio.produto.repository.RepositoryProduto;
import com.nike.dominio.usuario.model.Usuario;
import com.nike.dominio.usuario.repository.RepositoryUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/produtos")

public class ProdutoPage {

    @Autowired
    private RepositoryProduto repository;

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RepositoryCategoria repositoryCategoria;

    @GetMapping()
    public ModelAndView page(Model model, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("produtos");
        mv.addObject("currentUrl", request.getRequestURI());
        mv.addObject("produtos", repository.findAll());
        mv.addObject("categorias", repositoryCategoria.findAll());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            Usuario usuario = repositoryUser.findByEmail(username);
            if (usuario != null) {
                Long userId = usuario.getId();
                mv.addObject("userId", userId);
            }
        }

        return mv;
    }
}

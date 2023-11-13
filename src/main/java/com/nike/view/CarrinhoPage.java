package com.nike.view;

import com.nike.dominio.carrinho.repository.RepositoryCarrinho;
import com.nike.dominio.usuario.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoPage {

    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private RepositoryCarrinho carrinhoRepository;

    @GetMapping()
    public ModelAndView page(Principal principal) {
        String email = principal.getName();
        var user = userRepository.findByEmail(email);
        ModelAndView mv = new ModelAndView("carrinho");
        mv.addObject("user", user);
        try {
            mv.addObject("carrinho", carrinhoRepository.findByUsuarioId(user.getId()));
            mv.addObject("produtos", carrinhoRepository.findByUsuarioId(user.getId()).getProdutos());
            mv.addObject("carrinhoId", carrinhoRepository.findByUsuarioId(user.getId()).getId());
        } catch (Exception e) {
            mv.addObject("carrinho", null);
            mv.addObject("produtos", null);
        }
        return mv;
    }
}

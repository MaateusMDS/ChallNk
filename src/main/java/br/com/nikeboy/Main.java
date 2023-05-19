package br.com.nikeboy;

import br.com.nikeboy.model.Categoria;
import br.com.nikeboy.model.Produto;
import br.com.nikeboy.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        var usuario1 = new Usuario();
        usuario1.setNome("Luca");
        usuario1.setSobrenome("Spiller");
        usuario1.setEmail("lukspiller01@gmail.com");
        usuario1.setSenha("luca123");



        var categoria1 = new Categoria();
        categoria1.setNome("Corrida");

        var categoria2 = new Categoria();
        categoria2.setNome("SNKRS");

        var categoria3 = new Categoria();
        categoria3.setNome("Futebol");

        var categoria4 = new Categoria();
        categoria4.setNome("Casual");

        var categoria5 = new Categoria();
        categoria5.setNome("Basket");

        var categoria6 = new Categoria();
        categoria6.setNome("Acessorio");

        var categoria7 = new Categoria();
        categoria7.setNome("Skate");

        var categoria8 = new Categoria();
        categoria8.setNome("Natação");

        var produto1 = new Produto();
        produto1.setNome("Nike Air Force");
        produto.set

    }
}
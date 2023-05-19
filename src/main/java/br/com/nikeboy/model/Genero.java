package br.com.nikeboy.model;
public enum Genero {
    FEMININO(0, "Feminino", 'F'), MASCULINO(1, "Masculino", 'M'), UNISEX(2, "Unisex", 'U');

    private int id;
    private String nome;
    private Character sigla;


    Genero(int id, String nome, Character sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public int getId() {
        return id;
    }

    public Genero setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Genero setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Character getSigla() {
        return sigla;
    }

    public Genero setSigla(Character sigla) {
        this.sigla = sigla;
        return this;
    }


    @Override
    public String toString() {
        return String.valueOf(nome);
    }
}

package com.triagil.desafiotriagil.DTO;

public class PokemonDTO {

    Long id;

    String nome;

    Long altura;
    
    Long peso;

    public PokemonDTO(Long id, String nome, Long altura, Long peso) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getAltura() {
        return altura;
    }

    public void setAltura(Long altura) {
        this.altura = altura;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

}

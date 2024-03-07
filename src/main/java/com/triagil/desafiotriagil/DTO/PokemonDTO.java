package com.triagil.desafiotriagil.DTO;

public class PokemonDTO {

    String nome;

    Long altura;
    
    Long peso;

    public PokemonDTO(String nome, Long altura, Long peso) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
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

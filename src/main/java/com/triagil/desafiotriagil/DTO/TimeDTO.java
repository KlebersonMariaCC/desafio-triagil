package com.triagil.desafiotriagil.DTO;

import java.util.List;

import com.triagil.desafiotriagil.model.Pokemon;



public class TimeDTO {

     String owner;

    List<String> pokemons;

    

    public TimeDTO(String owner, List<String> pokemons) {
        this.owner = owner;
        this.pokemons = pokemons;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<String> pokemons) {
        this.pokemons = pokemons;
    }

    

}

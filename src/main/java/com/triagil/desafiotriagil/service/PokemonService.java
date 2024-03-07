package com.triagil.desafiotriagil.service;

import com.triagil.desafiotriagil.DTO.PokemonDTO;
import com.triagil.desafiotriagil.model.Pokemon;
import com.triagil.desafiotriagil.repository.PokemonRepository;

public class PokemonService {

    private PokemonRepository pokemonRepository;

    public Pokemon criarPokemon(PokemonDTO pokemonDTO) {
        
        Pokemon pokemon = new Pokemon(pokemonDTO.getNome(),pokemonDTO.getAltura(),pokemonDTO.getPeso());

        pokemonRepository.save(pokemon);

        return pokemon;

        
    }

}

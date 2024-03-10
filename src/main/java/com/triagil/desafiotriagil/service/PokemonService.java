package com.triagil.desafiotriagil.service;

import com.triagil.desafiotriagil.DTO.PokemonDTO;
import com.triagil.desafiotriagil.model.Pokemon;

public interface PokemonService {

    public Pokemon criarPokemon(PokemonDTO pokemonDTO);
}

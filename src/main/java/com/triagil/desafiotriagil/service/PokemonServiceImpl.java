package com.triagil.desafiotriagil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triagil.desafiotriagil.DTO.PokemonDTO;
import com.triagil.desafiotriagil.model.Pokemon;
import com.triagil.desafiotriagil.repository.PokemonRepository;


@Service
public class PokemonServiceImpl implements PokemonService {
    
    @Autowired
    private PokemonRepository pokemonRepository;

    public Pokemon criarPokemon(PokemonDTO pokemonDTO) {
        
        Pokemon pokemon = new Pokemon(pokemonDTO.getId(),pokemonDTO.getNome(),pokemonDTO.getAltura(),pokemonDTO.getPeso());

        pokemonRepository.save(pokemon);

        return pokemon;

        
    }

}

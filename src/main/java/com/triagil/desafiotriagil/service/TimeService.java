package com.triagil.desafiotriagil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.triagil.desafiotriagil.DTO.PokemonDTO;
import com.triagil.desafiotriagil.DTO.TimeDTO;
import com.triagil.desafiotriagil.model.PokeApiResponse;
import com.triagil.desafiotriagil.model.Pokemon;
import com.triagil.desafiotriagil.model.Time;
import com.triagil.desafiotriagil.repository.TimeRepository;

@Service
public class TimeService {

    @Autowired
    TimeRepository timeRepository;

    RestTemplate restTemplate;

    String url = "https://pokeapi.co/api/v2/pokemon/";

    PokemonService pokemonService;

    public List<Time> listarTimes() {
        return timeRepository.findAll();
    }

    public Time cadastraTime(TimeDTO timeDTO) {

        List<Pokemon> pokemons;

        for (String nomePokemon : timeDTO.getPokemons()) {

            PokeApiResponse response =  restTemplate.getForObject(url + nomePokemon, PokeApiResponse.class);

            

            if (response!= null) {
                
                PokemonDTO pokemonDTO = new PokemonDTO(response.nome(), response.altura(),response.peso());

                Pokemon pokemon = pokemonService.criarPokemon(pokemonDTO);

                pokemons.add(pokemon);

            } else {
                return Erro
            }



        }

        timeRepository.save(time);
    }

}

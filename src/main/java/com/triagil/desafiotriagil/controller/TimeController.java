package com.triagil.desafiotriagil.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.triagil.desafiotriagil.DTO.PokemonDTO;
import com.triagil.desafiotriagil.DTO.TimeDTO;
import com.triagil.desafiotriagil.model.PokeApiResponse;
import com.triagil.desafiotriagil.model.Pokemon;
import com.triagil.desafiotriagil.model.Time;
import com.triagil.desafiotriagil.service.PokemonService;
import com.triagil.desafiotriagil.service.TimeService;
import com.triagil.desafiotriagil.util.ErroPokemon;
import com.triagil.desafiotriagil.util.ErroTime;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TimeController {

    @Autowired
    private TimeService timeService;

    @Autowired
    PokemonService pokemonService;

    String url = "https://pokeapi.co/api/v2/pokemon/";

    private RestTemplate restTemplate;

    @GetMapping(value= "/api/teams")
    public ResponseEntity<?> listarTimes() {

        List<Time> times = timeService.listarTimes();

        if (times.isEmpty()) {
            return ErroTime.erroTimesNaoCadastrados();
        }

        return new ResponseEntity<List<Time>>(times,HttpStatus.OK);
    }

    @PostMapping("api/teams")
    public ResponseEntity<?> cadastrarTime(@RequestBody TimeDTO timeDTO, UriComponentsBuilder ucBuilder) {
        
        List<Time> times = timeService.listarTimes();

        List<Pokemon> pokemons =  new ArrayList<Pokemon>();

        for (String nomePokemon : timeDTO.getPokemons()) {

            PokeApiResponse response =  restTemplate.getForObject(url + nomePokemon, PokeApiResponse.class);

            

            if (response!= null) {
                
                PokemonDTO pokemonDTO = new PokemonDTO(response.name(), response.height(),response.weight());

                Pokemon pokemon = pokemonService.criarPokemon(pokemonDTO);

                pokemons.add(pokemon);

            } else {
                return ErroPokemon.erroPokemonNaoEncontrado();
            }

        }
         
        Time time = timeService.CriaTime(timeDTO.getOwner(),pokemons);

        if (times.contains(time)) {
            return ErroTime.erroTimeJaCadastrado();
        }
        timeService.cadastraTime(time);
         return new ResponseEntity<Time>(time,HttpStatus.CREATED);

    }

}

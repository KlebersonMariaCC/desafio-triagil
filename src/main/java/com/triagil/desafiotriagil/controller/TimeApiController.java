package com.triagil.desafiotriagil.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.triagil.desafiotriagil.DTO.PokemonDTO;
import com.triagil.desafiotriagil.DTO.TimeDTO;
import com.triagil.desafiotriagil.model.Pokemon;
import com.triagil.desafiotriagil.model.Time;
import com.triagil.desafiotriagil.service.PokemonService;
import com.triagil.desafiotriagil.service.TimeService;
import com.triagil.desafiotriagil.util.ErroPokemon;
import com.triagil.desafiotriagil.util.ErroTime;
import com.triagil.desafiotriagil.util.TimeResponse;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class TimeApiController {

    static final String  CADASTRO_BEM_SUCEDIDO = "Cadastro bem Sucedido!!!";

    @Autowired
    private TimeService timeService;

    @Autowired
    private PokemonService pokemonService;

    private String url = "https://pokeapi.co/api/v2/pokemon/";

    private RestTemplate restTemplate = new RestTemplate();



    @GetMapping("/teams")
    public ResponseEntity<?> listarTimes() {
        
        List<Time> times = timeService.listarTimes();

        if (times.isEmpty()) {
            return ErroTime.erroTimesNaoCadastrados();
        }

        Map<Long, Time> timesMap = new HashMap<>();

        for (Time time : times) {
            
            timesMap.put(time.getId(),time);
        }


        return new ResponseEntity<Map<Long, Time>>(timesMap,HttpStatus.OK);
    }

    

    @PostMapping("/teams")
    public ResponseEntity<?> cadastrarTime(@RequestBody TimeDTO timeDTO, UriComponentsBuilder ucBuilder) {
        
        List<Time> times = timeService.listarTimes();

        List<Pokemon> pokemons =  new ArrayList<Pokemon>();

        for (String nomePokemon : timeDTO.getTeam()) {

            Pokemon response =  restTemplate.getForObject(url + nomePokemon, Pokemon.class);

            if (response != null) {
                
                PokemonDTO pokemonDTO = new PokemonDTO(response.getId(), response.getName(), response.getHeight(),response.getWeight());

                Pokemon pokemon = pokemonService.criarPokemon(pokemonDTO);

                pokemons.add(pokemon);

            } else {
                return ErroPokemon.erroPokemonNaoEncontrado();
            }

        }
         
        Time time = timeService.CriaTime(timeDTO.getUser(),pokemons);

        if (times.contains(time)) {
            return ErroTime.erroTimeJaCadastrado();
        }
        timeService.cadastraTime(time);
        Map<String,TimeResponse> timeResponse = new HashMap<>();
        timeResponse.put(this.CADASTRO_BEM_SUCEDIDO,new TimeResponse(time.getId().toString(),time));
        return new ResponseEntity<Map<String,TimeResponse>>(timeResponse,HttpStatus.CREATED);

    }

    @GetMapping("{user}")
    public ResponseEntity<?> listarTimesPorUsuario(@RequestParam String user) {
        if (user == "" || user == null) {
            return new ErroUsuario.usuarioNãoPassado();
        }
    }
    

}

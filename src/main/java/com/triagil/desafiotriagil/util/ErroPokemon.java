package com.triagil.desafiotriagil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroPokemon {

    static final String POKEMON_NAO_ENCONTRADO = "Pokemon não encontrado";



    public ResponseEntity<CustomErrorType> erroPOkemonNaoEncontrado() {

        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(POKEMON_NAO_ENCONTRADO)), HttpStatus.NOT_FOUND);
    }

}

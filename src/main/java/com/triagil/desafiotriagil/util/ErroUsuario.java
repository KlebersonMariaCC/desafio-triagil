package com.triagil.desafiotriagil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroUsuario {

    static final String USUARIO_NAO_PASSADO = "Usuário não passado como parâmetro na requisição";
    static final String USUARIO_SEM_TIMES_CADASTRADOS = "Usuário Sem Times Cadastrados";

    public static ResponseEntity<CustomErrorType> erroUsuarioNaoPassado() {

        return new ResponseEntity<CustomErrorType>(new CustomErrorType(USUARIO_NAO_PASSADO), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType> erroUsuarioSemTimesCadastrados() {

        return new ResponseEntity<CustomErrorType>(new CustomErrorType(USUARIO_SEM_TIMES_CADASTRADOS), HttpStatus.NOT_FOUND);
    }

}

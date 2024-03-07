package com.triagil.desafiotriagil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroTime {


    static final String TIMES_NAO_CADASTRADOS = "Não há time cadastrados";
    
    static final String TIME_JA_CADASTRADO = "Time já cadastrado";



    public static ResponseEntity<CustomErrorType> erroTimesNaoCadastrados() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroTime.TIMES_NAO_CADASTRADOS)),HttpStatus.NO_CONTENT);

    }



    public static ResponseEntity<?> erroTimeJaCadastrado() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroTime.TIME_JA_CADASTRADO)),HttpStatus.CONFLICT);
    }

}

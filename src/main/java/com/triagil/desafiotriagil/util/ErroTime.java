package com.triagil.desafiotriagil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroTime {


    static final String TIMES_NAO_CADASTRADOS = "Não há times cadastrados";
    
    static final String TIME_JA_CADASTRADO = "Time já cadastrado";



    public static ResponseEntity<CustomErrorType> erroTimesNaoCadastrados() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroTime.TIMES_NAO_CADASTRADOS),HttpStatus.NOT_FOUND);

    }



    public static ResponseEntity<?> erroTimeJaCadastrado() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroTime.TIME_JA_CADASTRADO)),HttpStatus.CONFLICT);
    }

    

}

package com.triagil.desafiotriagil.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiResponse(String nome , Long altura, Long peso) {


    



}
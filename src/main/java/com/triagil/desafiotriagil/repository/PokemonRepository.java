package com.triagil.desafiotriagil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.triagil.desafiotriagil.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

}

package com.triagil.desafiotriagil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triagil.desafiotriagil.model.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

}

package com.triagil.desafiotriagil.service;

import java.util.List;

import com.triagil.desafiotriagil.model.Pokemon;
import com.triagil.desafiotriagil.model.Time;

public interface TimeService {


    public List<Time> listarTimes() ;

    public void cadastraTime(Time time) ;

    public Time CriaTime(String owner, List<Pokemon> pokemons);

    public List<Time> listarTimesPorUsuario(String owner);

}

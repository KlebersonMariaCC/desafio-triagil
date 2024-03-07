package com.triagil.desafiotriagil.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.triagil.desafiotriagil.model.Pokemon;
import com.triagil.desafiotriagil.model.Time;
import com.triagil.desafiotriagil.repository.TimeRepository;

@Service
public class TimeService {

    @Autowired
    TimeRepository timeRepository;

    public List<Time> listarTimes() {
        return timeRepository.findAll();
    }

    public void cadastraTime(Time time) {

        timeRepository.save(time);
    }

    public Time CriaTime(String owner, List<Pokemon> pokemons) {
        
        Time time = new Time(owner,pokemons);

        return time;
    }


}

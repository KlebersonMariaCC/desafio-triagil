package com.triagil.desafiotriagil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triagil.desafiotriagil.model.Time;

@Repository
public interface TimeRepository  extends JpaRepository<Time, Long>{

    



}

package com.triagil.desafiotriagil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.triagil.desafiotriagil.DTO.TimeDTO;
import com.triagil.desafiotriagil.model.Time;
import com.triagil.desafiotriagil.service.TimeService;
import com.triagil.desafiotriagil.util.ErroTime;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping(value= "/api/teams")
    public ResponseEntity<?> listarTimes() {

        List<Time> times = timeService.listarTimes();

        if (times.isEmpty()) {
            return ErroTime.erroTimesNaoCadastrados();
        }

        return new ResponseEntity<List<Time>>(times,HttpStatus.OK);
    }

    @PostMapping("api/teams")
    public ResponseEntity<?> cadastrarTime(@RequestBody TimeDTO timeDTO, UriComponentsBuilder ucBuilder) {
        
        List<Time> times = timeService.listarTimes();

         

         if (times.contains(time)) {
            return ErroTime.erroTimeJaCadastrado();
         }

        Time time = timeService.cadastraTime(timeDTO);

         return new ResponseEntity<Time>(time,HttpStatus.CREATED);

    }

}

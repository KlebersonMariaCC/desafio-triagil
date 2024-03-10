package com.triagil.desafiotriagil.DTO;

import java.util.List;



public class TimeDTO {

     String user;

    List<String> team;

    

    public TimeDTO(String user, List<String> team) {
        this.user = user;
        this.team = team;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getTeam() {
        return team;
    }

    public void setTeam(List<String> team) {
        this.team = team;
    }

    

}

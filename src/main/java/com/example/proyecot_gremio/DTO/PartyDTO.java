package com.example.proyecot_gremio.DTO;

import java.util.List;

import lombok.Data;

@Data
public class PartyDTO {
    private Integer id;
    private String nombre;
    private List<String> nombresAventureros;

}

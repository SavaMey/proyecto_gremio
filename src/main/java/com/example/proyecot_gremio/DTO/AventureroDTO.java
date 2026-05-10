package com.example.proyecot_gremio.DTO;

import java.util.List;

import lombok.Data;

@Data
public class AventureroDTO {
    private Integer id;
    private String nombre;
    private String nombreParty;
    private String nombreProfesion;
    private List<String> nombrePociones;

}

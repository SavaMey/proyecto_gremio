package com.example.proyecot_gremio.DTO;

import lombok.Data;

@Data
public class MisionDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer nivel;
    private Integer exp_recompensa;
    private Integer oro_recompensa;
}

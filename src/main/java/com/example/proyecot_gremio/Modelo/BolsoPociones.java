package com.example.proyecot_gremio.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bolso_pocion")
public class BolsoPociones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "aventurero_id")
    private Aventurero aventurero;

    @ManyToOne
    @JoinColumn(name = "pocion_id")
    private Pocion pocion;

    

}

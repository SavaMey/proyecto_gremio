package com.example.proyecot_gremio.Repository;

import org.springframework.stereotype.Repository;

import com.example.proyecot_gremio.DTO.AventureroArmadoDTO;
import com.example.proyecot_gremio.Modelo.Aventurero;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AventureroRepository extends JpaRepository<Aventurero, Integer> {
    @Query("SELECT new com.example.proyecot_gremio.DTO.AventureroArmadoDTO(a.nombre, e.arma.nombre) " +
        "FROM Aventurero a " +
        "JOIN a.equipoEquipado e")

    List<AventureroArmadoDTO> buscarSoloAventurerosArmados();

}

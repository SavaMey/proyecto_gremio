package com.example.proyecot_gremio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecot_gremio.Modelo.Pocion;

@Repository
public interface PocionRepository extends JpaRepository<Pocion, Integer> {

}

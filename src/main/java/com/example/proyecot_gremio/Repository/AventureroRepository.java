package com.example.proyecot_gremio.Repository;

import org.springframework.stereotype.Repository;

import com.example.proyecot_gremio.Modelo.Aventurero;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AventureroRepository extends JpaRepository<Aventurero, Integer> {

}

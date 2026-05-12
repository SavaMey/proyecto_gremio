package com.example.proyecot_gremio.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.proyecot_gremio.Modelo.Mision;

@Repository
public interface MisionRepository extends JpaRepository <Mision, Integer>{

    @Query("SELECT x FROM Mision x WHERE x.gremio.id = :gremioId AND x.estado = true")
    List<Mision> findMisionesCompletadas(@Param("gremioId") Integer gremioId);
}

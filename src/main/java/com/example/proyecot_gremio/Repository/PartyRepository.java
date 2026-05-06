package com.example.proyecot_gremio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecot_gremio.Modelo.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Integer> {

}

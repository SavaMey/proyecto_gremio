package com.example.proyecot_gremio.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.GremioDTO;
import com.example.proyecot_gremio.DTO.PartyDTO;
import com.example.proyecot_gremio.Modelo.Aventurero;
import com.example.proyecot_gremio.Modelo.Gremio;
import com.example.proyecot_gremio.Modelo.Party;
import com.example.proyecot_gremio.Repository.AventureroRepository;
import com.example.proyecot_gremio.Repository.FaccionRepository;
import com.example.proyecot_gremio.Repository.GremioRepository;
import com.example.proyecot_gremio.Repository.MisionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GremioService {

    @Autowired
    private GremioRepository gremioRepository;

    @Autowired
    private AventureroRepository aventureroRepository;

    @Autowired
    private MisionRepository misionRepository;

    @Autowired
    private FaccionRepository faccionRepository;
 
}

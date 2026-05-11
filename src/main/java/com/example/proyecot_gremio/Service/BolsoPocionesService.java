package com.example.proyecot_gremio.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.BolsoPocionesDTO;
import com.example.proyecot_gremio.Modelo.Aventurero;
import com.example.proyecot_gremio.Modelo.BolsoPociones;
import com.example.proyecot_gremio.Modelo.Pocion;
import com.example.proyecot_gremio.Repository.AventureroRepository;
import com.example.proyecot_gremio.Repository.BolsoPocionesRepository;
import com.example.proyecot_gremio.Repository.PocionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BolsoPocionesService {
    
    @Autowired
    private BolsoPocionesRepository bolsoPocionesRepository;

    @Autowired
    private AventureroRepository aventureroRepository;

    @Autowired
    private PocionRepository pocionRepository;


    public BolsoPocionesDTO agregarPocionAlBolso(Integer aventureroId, Integer pocionId, Integer cantidad) {
        Aventurero aventurero = aventureroRepository.findById(aventureroId)
            .orElseThrow(() -> new RuntimeException("¡El aventurero no existe!"));
        Pocion pocion = pocionRepository.findById(pocionId)
            .orElseThrow(() -> new RuntimeException("¡La poción no existe!"));
        BolsoPociones instancia = BolsoPociones.builder()
            .aventurero(aventurero)
            .pocion(pocion)
            .cantidad(cantidad) 
            .build();

        BolsoPociones guardado = bolsoPocionesRepository.save(instancia);
        return convertirADTO(guardado);
    }

    private BolsoPocionesDTO convertirADTO(BolsoPociones instancia) {
    BolsoPocionesDTO dto = new BolsoPocionesDTO();
    dto.setId(instancia.getId());
    dto.setCantidad(instancia.getCantidad());
    dto.setNombresAventureros(instancia.getAventurero().getNombre());
    dto.setNombresPociones(instancia.getPocion().getNombre());
    return dto;
    }
}

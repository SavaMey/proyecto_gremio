package com.example.proyecot_gremio.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.EquipamientoDTO;
import com.example.proyecot_gremio.Modelo.Arma;
import com.example.proyecot_gremio.Modelo.Aventurero;
import com.example.proyecot_gremio.Modelo.Equipamiento;
import com.example.proyecot_gremio.Repository.ArmaRepository;
import com.example.proyecot_gremio.Repository.AventureroRepository;
import com.example.proyecot_gremio.Repository.EquipamientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EquipamientoService {

    @Autowired
    private EquipamientoRepository equipamientoRepository;

    @Autowired
    private AventureroRepository aventureroRepository;

    @Autowired
    private ArmaRepository armaRepository;

    public EquipamientoDTO agregarArmaAlAventurero(Integer aventureroId, Integer armaId){
        Aventurero aventurero = aventureroRepository.findById(aventureroId)
            .orElseThrow(() -> new RuntimeException("¡El aventurero no existe!"));
        Arma arma = armaRepository.findById(armaId)
            .orElseThrow(() -> new RuntimeException("¡El arma no existe!"));
        Equipamiento instancia = Equipamiento.builder()
            .aventurero(aventurero)
            .arma(arma)
            .build();
        Equipamiento guardado = equipamientoRepository.save(instancia);
        return convertirADTO(guardado);
    }

    private EquipamientoDTO convertirADTO(Equipamiento instancia) {
    EquipamientoDTO dto = new EquipamientoDTO();
    dto.setId(instancia.getId());
    dto.setNombresAventureros(instancia.getAventurero().getNombre());
    dto.setNombresArmas(instancia.getArma().getNombre());
    return dto;
    }

}

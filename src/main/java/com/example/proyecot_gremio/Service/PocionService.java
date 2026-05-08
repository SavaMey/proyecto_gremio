package com.example.proyecot_gremio.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.AventureroDTO;
import com.example.proyecot_gremio.DTO.PocionDTO;
import com.example.proyecot_gremio.Modelo.Aventurero;
import com.example.proyecot_gremio.Modelo.Pocion;
import com.example.proyecot_gremio.Repository.PocionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PocionService {

    @Autowired
    private PocionRepository pocionRepository;

    public List<PocionDTO> obtenerTodos(){
        return pocionRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public PocionDTO buscarPorId(Integer id){
        Pocion pocion = pocionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Esta poción no existe."));
        return convertirADTO(pocion);
    }

    private PocionDTO convertirADTO(Pocion pocion) {
        PocionDTO dto = new PocionDTO();
        dto.setId(pocion.getId());
        dto.setNombre(pocion.getNombre());
        return dto;
    }

}

package com.example.proyecot_gremio.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.ArmaDTO;
import com.example.proyecot_gremio.Modelo.Arma;
import com.example.proyecot_gremio.Repository.ArmaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ArmaService {

    @Autowired
    private ArmaRepository armaRepository;

    public List<ArmaDTO> obtenerTodas(){
        return armaRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public ArmaDTO buscarPorId(Integer id){
        Arma arma = armaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Esta arma no existe."));
        return convertirADTO(arma);
    }

    public String eliminarArma(Integer id){
        try {
            Arma arma = armaRepository.findById(id)
            .orElseThrow(()->new RuntimeException("No se puede eliminar: el arma con Id"+id+" no existe."));
            armaRepository.delete(arma);
            return "El arma '"+arma.getNombre()+"' ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Arma guardarArma(Arma arma){
        return armaRepository.save(arma);
    }

    private ArmaDTO convertirADTO(Arma arma) {
        ArmaDTO dto = new ArmaDTO();
        dto.setId(arma.getId());
        dto.setNombre(arma.getNombre());
        dto.setDescripcion(arma.getDescripcion());
        dto.setDañoArma(arma.getDañoArma());
        return dto;
    }

}

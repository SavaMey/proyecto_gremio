package com.example.proyecot_gremio.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.PocionDTO;
import com.example.proyecot_gremio.Modelo.Pocion;
import com.example.proyecot_gremio.Repository.PocionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PocionService {

    @Autowired
    private PocionRepository pocionRepository;

    public List<PocionDTO> obtenerTodas(){
        return pocionRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public PocionDTO buscarPorId(Integer id){
        Pocion pocion = pocionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Esta poción no existe."));
        return convertirADTO(pocion);
    }

    public String eliminarPocion(Integer id){
        try {
            Pocion pocion = pocionRepository.findById(id)
            .orElseThrow(()->new RuntimeException("No se puede eliminar: la poción con Id"+id+" no está existe."));
            pocionRepository.delete(pocion);
            return "La '"+pocion.getNombre()+"' ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Pocion guardarPocion(Pocion pocion){
        return pocionRepository.save(pocion);
    }

    private PocionDTO convertirADTO(Pocion pocion) {
        PocionDTO dto = new PocionDTO();
        dto.setId(pocion.getId());
        dto.setNombre(pocion.getNombre());
        dto.setDescripcion(pocion.getDescripcion());
        return dto;
    }



}

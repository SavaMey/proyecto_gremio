package com.example.proyecot_gremio.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.AventureroArmadoDTO;
import com.example.proyecot_gremio.DTO.AventureroDTO;
import com.example.proyecot_gremio.Modelo.Aventurero;
import com.example.proyecot_gremio.Modelo.Profesion;
import com.example.proyecot_gremio.Repository.AventureroRepository;
import com.example.proyecot_gremio.Repository.ProfesionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AventureroService {
    
    @Autowired
    private AventureroRepository aventureroRepository;
    private ProfesionRepository profesionRepository;

    public List<AventureroDTO> obtenerTodos() {
        return aventureroRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public AventureroDTO buscarPorId(Integer id){
        Aventurero aventurero = aventureroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El aventurero no está en los registros."));
        return convertirADTO(aventurero);
    }

    public String eliminar(Integer id){
        try {
            Aventurero aventurero = aventureroRepository.findById(id)
            .orElseThrow(()->new RuntimeException("No se puede eliminar: el aventurero con Id"+id+" no está registrado."));
            aventureroRepository.delete(aventurero);
            return "El aventurero '"+aventurero.getNombre()+"' ha sido eliminado exitosamente de los registros.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Aventurero guardarAventurero(Aventurero aventurero){
        return aventureroRepository.save(aventurero);
    }
    
    public Aventurero actualizarAventurero(Integer id,Aventurero aventurero){
        Aventurero aven = aventureroRepository.findById(id).orElseThrow(() -> new RuntimeException("El aventurero no está en los registros."));
        if(aventurero.getNombre() != null){
            aven.setNombre(aventurero.getNombre());
        }
        return aventureroRepository.save(aven);
    }

    public AventureroDTO asignarProfesion(Integer aventureroId, Integer profesionId) {
        Aventurero aventurero = aventureroRepository.findById(aventureroId)
            .orElseThrow(() -> new RuntimeException("Aventurero no encontrado"));
        Profesion profesion = profesionRepository.findById(profesionId)
            .orElseThrow(() -> new RuntimeException("Profesión no encontrada"));
        aventurero.setProfesion(profesion);
        Aventurero guardado = aventureroRepository.save(aventurero);
        return convertirADTO(guardado);
    }

    public List<AventureroArmadoDTO> obtenerReporteDeArmados() {
        return aventureroRepository.buscarSoloAventurerosArmados();
    }

    private AventureroDTO convertirADTO(Aventurero aventurero) {
        AventureroDTO dto = new AventureroDTO();
        dto.setId(aventurero.getId());
        dto.setNombre(aventurero.getNombre());

        if (aventurero.getParty() != null) {
            dto.setNombreParty(aventurero.getParty().getNombre());
        }else{
            dto.setNombreParty("Lobo solitario, auuu");
        }

        
        if (aventurero.getProfesion() != null) {
            dto.setNombreProfesion(aventurero.getProfesion().getNombre());
        } else {
            dto.setNombreProfesion("Desempleado (Aún no elige su camino)"); 
        }
        return dto;
    }

}

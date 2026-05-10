package com.example.proyecot_gremio.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecot_gremio.DTO.ReputacionDTO;
import com.example.proyecot_gremio.Modelo.Reputacion;
import com.example.proyecot_gremio.Repository.ReputacionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReputacionService {
    
    @Autowired
    private ReputacionRepository reputacionRepository;

    public List<ReputacionDTO> obtenerTodos() {
        return reputacionRepository.findAll().stream().map(this::convertirADTO).toList();
    }


    public ReputacionDTO buscarPorId(Integer id){
        Reputacion reputacion = reputacionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe la reputacion indicada."));
        return convertirADTO(reputacion);
    }

    public String eliminar(Integer id){
        try {
            Reputacion reputacion = reputacionRepository.findById(id)
            .orElseThrow(()->new RuntimeException("No es posible eliminar, reputacion "+id+" no existe en sistema"));
            reputacionRepository.delete(reputacion);
            return "La reputacion '"+reputacion.getNombre()+"' Fue eliminada de manera exitosa";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Reputacion guardarReputacion(Reputacion reputacion){
        return reputacionRepository.save(reputacion);
    }

    public Reputacion actualizarReputacion(Integer id,Reputacion reputacion){
        Reputacion repu = reputacionRepository.findById(id).orElseThrow(() -> new RuntimeException("La reputacion no existe."));
        if(reputacion.getNivel() != null){
            repu.setNivel(reputacion.getNivel());
            repu.setNombre(nomReputacion(reputacion.getNivel()));
        }
        return reputacionRepository.save(repu);
    }

    private String nomReputacion(Integer nivel) {
        if (nivel >= -10 && nivel < -5) 
            return "Vengativo";
        if (nivel >= -5 && nivel < 0) 
            return "Hostil";
        if (nivel == 0) 
            return "Neutro";
        if (nivel >= 0 && nivel < 5) 
            return "Amistoso";
        else
            return "Benevolente";

    }

    private ReputacionDTO convertirADTO(Reputacion reputacion) {
        ReputacionDTO dto = new ReputacionDTO();
        dto.setId(reputacion.getId());
        dto.setNombre(reputacion.getNombre());
        dto.setNivel(reputacion.getNivel());
        return dto;
    }

}
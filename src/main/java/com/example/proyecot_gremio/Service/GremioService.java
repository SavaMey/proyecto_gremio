package com.example.proyecot_gremio.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.AventureroDTO;
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

    public List<GremioDTO> obtenerTodos() {
        return gremioRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public GremioDTO buscarPorId(Integer id){
        Gremio gremio = gremioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El gremio no existe"));
        return convertirADTO(gremio);
    }

    public String eliminar(Integer id){
        try {
            Gremio gremio = gremioRepository.findById(id)
            .orElseThrow(()->new RuntimeException("No se puede eliminar un gremio inexistente"));
            gremioRepository.delete(gremio);
            return "El gremio ha sido eliminado exitosamente de los registros.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Gremio guardarGremio(Gremio gremio){
        return gremioRepository.save(gremio);
    }

    public Gremio actualizarGremio(Integer id, Gremio gremio){
        Gremio newGremio = gremioRepository.findById(id).orElseThrow(() -> new RuntimeException("El gremio no existe en los registros."));
        if(gremio.getNombre() != null){
            newGremio.setNombre(gremio.getNombre());
        }
        if(gremio.getOro() != null){
            newGremio.setOro(gremio.getOro());
        }
        return gremioRepository.save(newGremio);
    }

    private GremioDTO convertirADTO(Gremio gremio) {
        GremioDTO dto = new GremioDTO();
        dto.setId(gremio.getId());
        dto.setNombre(gremio.getNombre());
        dto.setOro(gremio.getOro());

        if (aventurero.getParty() != null) {
            dto.setNombreParty(aventurero.getParty().getNombre());
        }else{
            dto.setNombreParty("Lobo solitario, auuu");
        }
        return dto;
    }

 
}

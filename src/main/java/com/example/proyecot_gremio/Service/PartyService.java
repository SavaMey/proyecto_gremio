package com.example.proyecot_gremio.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.PartyDTO;
import com.example.proyecot_gremio.Modelo.Aventurero;
import com.example.proyecot_gremio.Modelo.Party;
import com.example.proyecot_gremio.Repository.AventureroRepository;
import com.example.proyecot_gremio.Repository.PartyRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PartyService {
     @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private AventureroRepository aventureroRepository;

    public List<PartyDTO> obtenerTodas() {
        return partyRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PartyDTO buscarPorId(Integer id) {
        Party party = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La party no existe!"));
        return convertirADTO(party); // Transmutamos la party encontrada
    }

    public Party guardar(Party party) {
        return partyRepository.save(party);
    }

    public String añadirAventureroAParty(Integer partyId, Integer aventureroId) {
        Party party = partyRepository.findById(partyId)
            .orElseThrow(() -> new RuntimeException("Error: La Party no existe en los registros."));
        Aventurero aventurero = aventureroRepository.findById(aventureroId)
            .orElseThrow(() -> new RuntimeException("Error: El aventurero no existe en los registros."));
        aventurero.setParty(party); 
        aventureroRepository.save(aventurero);

        return "El aventurero '" + aventurero.getNombre() + "' se unió a la party: " + party.getNombre();
    }

    public String eliminarAventureroDeParty(Integer partyId, Integer aventureroId) {
        Aventurero aventurero = aventureroRepository.findById(aventureroId)
            .orElseThrow(() -> new RuntimeException("El aventurero no existe en los registros."));
        if (aventurero.getParty() != null && aventurero.getParty().getId().equals(partyId)) {
            aventurero.setParty(null);
            aventureroRepository.save(aventurero);
            return "El aventurero ha sido expulsado de la party y ahora trabaja sólo.";
        }
        return "Error: El aventurero no pertenece a esa party, no puedes expulsarlo.";
    }

    private PartyDTO convertirADTO(Party party) {
        PartyDTO dto = new PartyDTO();
        dto.setId(party.getId());
        dto.setNombre(party.getNombre());
        dto.setNivel(party.getNivel());
        
        if (party.getAventureros() != null) {
            dto.setNombresAventureros(party.getAventureros().stream()
                                    .map(Aventurero::getNombre)
                                    .toList());
        } else {
            dto.setNombresAventureros(new ArrayList<>());
        }
        
        return dto;
    }

}

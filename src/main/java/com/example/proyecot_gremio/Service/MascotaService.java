package com.example.proyecot_gremio.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecot_gremio.DTO.MascotaDTO;
import com.example.proyecot_gremio.Modelo.Mascota;
import com.example.proyecot_gremio.Modelo.Party;
import com.example.proyecot_gremio.Repository.MascotaRepository;
import com.example.proyecot_gremio.Repository.PartyRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private PartyRepository partyRepository;

    public List<MascotaDTO> obtenerTodas(){
        return mascotaRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public MascotaDTO buscarPorId(Integer id){
        Mascota mascota = mascotaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("La mascota no existe."));
        return convertirADTO(mascota);
    }

    public String eliminarMascota(Integer id){
        try {
            Mascota mascota = mascotaRepository.findById(id)
            .orElseThrow(()->new RuntimeException("No se puede eliminar: la mascota con Id"+id+" no existe."));
            mascotaRepository.delete(mascota);
            return "La mascota'"+mascota.getNombre()+"' ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Mascota guardarMascota(Mascota mascota){
        return mascotaRepository.save(mascota);
    }

    public MascotaDTO vincularAParty(Integer mascotaId, Integer partyId) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
            .orElseThrow(() -> new RuntimeException("¡Mascota no encontrada!"));
        Party party = partyRepository.findById(partyId)
            .orElseThrow(() -> new RuntimeException("¡La party no existe!"));
        if (party.getMascota() != null && !party.getMascota().getId().equals(mascotaId)) {
            throw new RuntimeException("¡Esta party ya tiene un compañero fiel asignado!");
        }
        mascota.setParty(party);
        Mascota guardada = mascotaRepository.save(mascota);
        return convertirADTO(guardada);
    }

    public MascotaDTO desvincularDeParty(Integer mascotaId) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
            .orElseThrow(() -> new RuntimeException("¡Mascota no encontrada en los registros!"));
        mascota.setParty(null);
        Mascota actualizada = mascotaRepository.save(mascota);
        return convertirADTO(actualizada);
    }

    private MascotaDTO convertirADTO(Mascota mascota) {
        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setDescripcion(mascota.getDescripcion());
        dto.setSalud(mascota.getSalud());
        if (mascota.getParty() != null) {
            dto.setNombreParty(mascota.getParty().getNombre());
        }
        return dto;
    }

}

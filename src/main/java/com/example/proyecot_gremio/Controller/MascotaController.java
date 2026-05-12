package com.example.proyecot_gremio.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.proyecot_gremio.DTO.MascotaDTO;
import com.example.proyecot_gremio.Modelo.Mascota;
import com.example.proyecot_gremio.Service.MascotaService;

@RestController
@RequestMapping("/api/v1/mascotas")

public class MascotaController {

    @Autowired
    private  MascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> todasLasMascotas() {
        List<MascotaDTO> mascotas = mascotaService.obtenerTodas();
        if (mascotas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mascotas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            MascotaDTO mas = mascotaService.buscarPorId(id);
            return new ResponseEntity<>(mas, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Mascota> agregarMascota(@RequestBody Mascota mascota) {
        try {
            Mascota guardado = mascotaService.guardarMascota(mascota);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarArma(@PathVariable Integer id) {
        String resultado = mascotaService.eliminarMascota(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{mascotaId}/party/{partyId}")
    public ResponseEntity<?> vincular(@PathVariable Integer mascotaId, @PathVariable Integer partyId) {
        try {
            MascotaDTO resultado = mascotaService.vincularAParty(mascotaId, partyId);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}/desvincular")
    public ResponseEntity<MascotaDTO> desvincularMascota(@PathVariable Integer id) {
        try {
            MascotaDTO resultado = mascotaService.desvincularDeParty(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

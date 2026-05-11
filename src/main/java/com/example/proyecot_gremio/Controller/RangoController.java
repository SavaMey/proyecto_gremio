package com.example.proyecot_gremio.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.proyecot_gremio.DTO.RangoDTO;
import com.example.proyecot_gremio.Modelo.Rango;
import com.example.proyecot_gremio.Service.RangoService;

@RestController
@RequestMapping("/api/v1/rango")
public class RangoController {

    @Autowired
    private RangoService rangoService;

    @GetMapping
    public ResponseEntity<List<RangoDTO>> todosLosRangos() {
        List<RangoDTO> rango = rangoService.obtenerTodos();
        if (rango.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rango, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RangoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            RangoDTO ran = rangoService.buscarPorId(id);
            return new ResponseEntity<>(ran, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Rango> agregarRango(@RequestBody Rango ran) {
        try {
            Rango guardado = rangoService.guardarRango(ran);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Rango> editarRango(@PathVariable Integer id, @RequestBody Rango ran) {
        try {
            Rango editado = rangoService.actualizarRango(id, ran);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rango> actualizarRango(@PathVariable Integer id, @RequestBody Rango ran){
        try{
            Rango newRan = rangoService.actualizarRango(id, ran);
            return new ResponseEntity<>(newRan, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRango(@PathVariable Integer id) {
        String resultado = rangoService.eliminar(id);
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}

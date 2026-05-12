package com.example.proyecot_gremio.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecot_gremio.DTO.BolsoPocionesDTO;
import com.example.proyecot_gremio.Service.BolsoPocionesService;

@RestController
@RequestMapping("/api/v1/bolso")
public class BolsoPocionesController {

    @Autowired
    private BolsoPocionesService bolsoPocionesService;

    @PostMapping("/agregar")
    public ResponseEntity<BolsoPocionesDTO> agregar(@RequestParam Integer aventureroId, 
                                                   @RequestParam Integer pocionId, 
                                                   @RequestParam Integer cantidad) {
        try {
            BolsoPocionesDTO resultado = bolsoPocionesService.agregarPocionAlBolso(aventureroId, pocionId, cantidad);
            return new ResponseEntity<>(resultado, HttpStatus.CREATED); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
    }
}




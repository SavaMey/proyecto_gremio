package com.example.proyecot_gremio.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecot_gremio.DTO.EquipamientoDTO;
import com.example.proyecot_gremio.Service.EquipamientoService;

@RestController
@RequestMapping("/api/v1/equipamiento")
public class EquipamientoController {

    @Autowired
    private EquipamientoService equipamientoService;

    @PostMapping("/agregar")
    public ResponseEntity<EquipamientoDTO> agregar(@RequestParam Integer aventureroId, 
                                                   @RequestParam Integer armaId) {
        try {
            EquipamientoDTO resultado = equipamientoService.agregarArmaAlAventurero(aventureroId, armaId);
            return new ResponseEntity<>(resultado, HttpStatus.CREATED); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
    }

}

package com.example.proyecot_gremio.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mascotas")

public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "El nombre es obligatorio")
    @Size(min = 3, max = 40, message = "El nombre debe tener entre 3 y 40 caracteres")
    @Column(nullable = false, length = 40)
    private String nombre;

    @NotBlank (message = "La descripcion es obligatoria")
    @Size(min = 3, max = 100, message = "La descripcion debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String descripcion;

    @Builder.Default
    @Min(value = 10, message = "La salud mínima de la mascota es 10")
    @Max(value = 999, message = "La salud máxima de la mascota es de 999")
    @Column(nullable = false)
    private Integer salud = 10;
    
    
//---------------------------------------------------------------------------------------------

    @OneToOne
    @JoinColumn(name = "party_id", unique = true)
    private Party party;



}

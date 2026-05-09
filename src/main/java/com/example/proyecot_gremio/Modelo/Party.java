package com.example.proyecot_gremio.Modelo;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parties")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @Size(min = 3, max = 70, message = "La party debe tener al menos 3 aventureros")
   @Column(nullable = false, length = 70)
   private String nombre;

   //-------------------------------------------------------------------------
   
   @OneToMany(mappedBy = "party")
   @ToString.Exclude
   private List<Aventurero> aventureros;
}

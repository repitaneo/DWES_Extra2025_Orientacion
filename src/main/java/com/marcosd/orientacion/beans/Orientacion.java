package com.marcosd.orientacion.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Orientacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorientacion")
    private Long idOrientacion;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, message = "El nombre debe contener al menos 3 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Pattern(regexp = "^[^@]+$", message = "El email no debe contener '@'. Solo introduce la parte antes de '@educastur.org'.")
    private String email;

    
    
    @OneToOne
    @JoinColumn(name = "centro")
    private Centro centro;

	

    @Column(name = "eliminado")
    private boolean eliminado = false; // Nuevo campo para eliminación lógica
    
    @OneToMany(mappedBy = "orientacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursos = new ArrayList<>();
}

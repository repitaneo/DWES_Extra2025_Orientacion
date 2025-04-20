package com.marcosd.orientacion.beans;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.marcosd.orientacion.service.FechaService;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Tutoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtutoria")
    private Long idTutoria;

    @NotBlank(message = "El nombre de la tutoria es obligatorio")
    @Size(min = 3, message = "El nombre debe contener al menos 3 caracteres")
    private String nombre;
    
    @NotBlank(message = "La persona que obstenta la tutoría es obligatorio")
    @Size(min = 3, message = "El nombre debe contener al menos 3 caracteres")
    private String tutor;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotNull(message = "El año es obligatorio")
    private Integer fecha;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    @ToString.Exclude
    private Curso curso;
    
    
    public Tutoria() {
    	
    	FechaService fs = new FechaService();
    	fecha = fs.getAnioLectivoActual();
    }
}


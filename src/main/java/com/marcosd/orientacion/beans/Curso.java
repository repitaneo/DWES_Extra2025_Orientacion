package com.marcosd.orientacion.beans;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcurso")
	private Long idCurso;
	
	@NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, message = "El nombre debe contener al menos 3 caracteres")
	private String nombre;
	    
	private String observaciones;
	
	
	@Column(name="archivarseptiembre")
	private boolean archivarSeptiembre;
	
	@ManyToOne
    @JoinColumn(name = "orientacionid", nullable = false)
	@ToString.Exclude
    private Orientacion orientacion;
	
	
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tutoria> tutorias = new ArrayList<>();
	
	
	
	
    public Curso(String nombreCurso, Orientacion orientacion) {
        this.nombre = nombreCurso;
        this.orientacion = orientacion;
        this.archivarSeptiembre = true;
        this.observaciones = "Creado de forma automática";
    }
    
    public Curso() {

    }
}

package com.marcosd.orientacion.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcurso")
	private Long idCurso;
	private String nombre;
	private String observaciones;
	@Column(name="archivarseptiembre")
	private boolean archivarSeptiembre;
	
}

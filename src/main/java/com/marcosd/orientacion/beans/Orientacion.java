package com.marcosd.orientacion.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orientacion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idorientacion")
	private Long idOrientacion;
	private String nombre;
	private String email;
	@Column(name="codigocentro")
	private String codigoCentro;
	@Column(name="nombrecentro")
	private String nombreCentro;
	
	
}

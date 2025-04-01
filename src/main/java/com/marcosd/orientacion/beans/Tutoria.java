package com.marcosd.orientacion.beans;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Tutoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtutoria")
	private Long idTutoria;
	private String nombre;
	private String email;
	
	@DateTimeFormat(pattern = "yyyy")
	private LocalDate anio;
	
}

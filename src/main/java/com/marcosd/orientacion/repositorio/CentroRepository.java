package com.marcosd.orientacion.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcosd.orientacion.beans.Centro;
import com.marcosd.orientacion.beans.Tutoria;


public interface CentroRepository extends JpaRepository<Centro, String>{
	
	List<Centro> findByOrientacionIsNullOrderByMunicipioAscNombreAsc();
	
}

package com.marcosd.orientacion.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcosd.orientacion.beans.Orientacion;

public interface OrientacionRepository extends JpaRepository<Orientacion, Long>{

	 List<Orientacion> findByEliminadoFalse(); // Solo recupera registros no eliminados
	 
}

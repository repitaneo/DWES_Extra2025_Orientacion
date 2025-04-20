package com.marcosd.orientacion.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import com.marcosd.orientacion.beans.Tutoria;


public interface TutoriaRepository extends JpaRepository<Tutoria, Long>{
	
}

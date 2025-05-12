package com.marcosd.orientacion.repositorio;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marcosd.orientacion.beans.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {

		Page<Actividad> findAll(Pageable pageable);
}

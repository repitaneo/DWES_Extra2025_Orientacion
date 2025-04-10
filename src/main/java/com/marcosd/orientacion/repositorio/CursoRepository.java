package com.marcosd.orientacion.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcosd.orientacion.beans.Curso;
import com.marcosd.orientacion.beans.Orientacion;

public interface CursoRepository extends JpaRepository<Curso, Long>{

}

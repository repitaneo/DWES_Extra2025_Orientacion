package com.marcosd.orientacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcosd.orientacion.beans.Curso;
import com.marcosd.orientacion.beans.Orientacion;
import com.marcosd.orientacion.repositorio.CursoRepository;
import com.marcosd.orientacion.repositorio.OrientacionRepository;

@Service
public class OrientacionService {

    @Autowired
    private OrientacionRepository orientacionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public void guardarOrientacionConCursos(Orientacion orientacion) {
        // Guardar la orientación primero (debe tener ID para relación)
        orientacionRepository.save(orientacion);

        // Comprobar si tiene cursos asociados en la BD
        List<Curso> cursosExistentes = cursoRepository.findByOrientacion(orientacion);
        if (cursosExistentes.isEmpty()) {
            List<Curso> cursosPredeterminados = List.of(
                new Curso("1ESO", orientacion),
                new Curso("2ESO", orientacion),
                new Curso("3ESO", orientacion),
                new Curso("4ESO", orientacion),
                new Curso("1Bachillerato", orientacion),
                new Curso("2Bachillerato", orientacion)
            );
            cursoRepository.saveAll(cursosPredeterminados);
        }
    }
}

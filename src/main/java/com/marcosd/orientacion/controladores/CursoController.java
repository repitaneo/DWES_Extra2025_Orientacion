package com.marcosd.orientacion.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marcosd.orientacion.beans.Curso;
import com.marcosd.orientacion.beans.Orientacion;
import com.marcosd.orientacion.repositorio.CursoRepository;
import com.marcosd.orientacion.repositorio.OrientacionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private OrientacionRepository orientacionRepository;

    // Método POST para recibir el formulario y guardarlo en la base de datos
    @PostMapping("/add/{id}")
    public ModelAndView saveOrientacion(@Valid @ModelAttribute("curso") Curso curso, 
    		@PathVariable("id") Long idOrientacion, BindingResult result) {
        
    	if (result.hasErrors()) {
            return new ModelAndView("redirect:/orientacion/"+idOrientacion);
        }
        // Aquí podrías guardar el objeto 'orientacion' en la base de datos
    	Orientacion orientacion = orientacionRepository.findById(idOrientacion).get();
    	curso.setOrientacion(orientacion);
        cursoRepository.save(curso);  // Llamada al servicio para guardar

        // Redirige a una vista de confirmación o lista de orientaciones
        ModelAndView modelAndView = new ModelAndView("redirect:/orientacion/"+idOrientacion);
        return modelAndView;
    }	
    

  
    // Eliminar una orientación (eliminación lógica)
    @GetMapping("/eliminar/{id}")
    public String eliminarOrientacion(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        
    	Orientacion orientacion = new Orientacion();
    	
    	Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            
        	Curso curso = optionalCurso.get();
            cursoRepository.deleteById(id);
            orientacion = curso.getOrientacion();
        }

        if(orientacion.getIdOrientacion()>0)
        	return "redirect:/orientacion/"+orientacion.getIdOrientacion();
        else return "redirect:/orientaciones";
    }    
    

	
}

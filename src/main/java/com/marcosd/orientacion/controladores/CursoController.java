package com.marcosd.orientacion.controladores;

import com.marcosd.orientacion.beans.Curso;
import com.marcosd.orientacion.beans.Orientacion;
import com.marcosd.orientacion.beans.Tutoria;
import com.marcosd.orientacion.repositorio.CursoRepository;
import com.marcosd.orientacion.repositorio.OrientacionRepository;
import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private OrientacionRepository orientacionRepository;

    // Método POST para recibir el formulario y guardarlo en la base de datos
    @PostMapping("/add/{id}")
    public ModelAndView saveOCurso(@Valid @ModelAttribute("curso") Curso curso, 
    		@PathVariable("id") Long idOrientacion, BindingResult result) {
        
    	if (result.hasErrors()) {
            return new ModelAndView("redirect:/orientacion/"+idOrientacion);
        }
    	
        // Aquí podrías guardar el objeto 'curso' en la base de datos
    	Optional<Orientacion> orientacionOptional = orientacionRepository.findById(idOrientacion);
    	
    	if(orientacionOptional.isPresent()) {
    		curso.setOrientacion(orientacionOptional.get());
    		cursoRepository.save(curso);  // Llamada al servicio para guardar
    	}

        // Redirige a una vista de confirmación o lista de orientaciones
        ModelAndView modelAndView = new ModelAndView("redirect:/orientacion/"+idOrientacion);
        return modelAndView;
    }	
    

  
    // Eliminar una orientación (eliminación lógica)
    @GetMapping("/del/{id}")
    public String eliminarCurso(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        
    	Orientacion orientacion = new Orientacion();
    	
    	Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            
        	Curso curso = optionalCurso.get();
        	if(curso.getTutorias().size()==0) {
        		cursoRepository.deleteById(id);
        	}
            orientacion = curso.getOrientacion();
        }

        if(orientacion.getIdOrientacion()>0)
        	return "redirect:/orientacion/"+orientacion.getIdOrientacion();
        else return "redirect:/orientacion";
    }    
    
    
    

    // getea
    @GetMapping("/{id}")
    public ModelAndView getCurso(@PathVariable Long id) {
    
    	ModelAndView modelAndView = null;
    	
    	Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            
        	Curso curso = optionalCurso.get();
        	modelAndView = new ModelAndView("curso/curso");
        	modelAndView.addObject("curso", curso);
        	modelAndView.addObject("tutoria", new Tutoria());
        }
        else modelAndView = new ModelAndView("redirect:/orientacion");
        
        return modelAndView;
    
    }
    
    
    @GetMapping("/edit/{id}")
    public ModelAndView editCurso(@PathVariable Long id) {
        
    	ModelAndView mav = new ModelAndView("curso/cursoForm");
        
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mav.addObject("curso", curso);
        return mav;
    }
    
    
    @PostMapping("/edit")
    public ModelAndView updateCurso(@ModelAttribute Curso curso) {
    	
        cursoRepository.save(curso);
        ModelAndView mav = new ModelAndView("redirect:/curso/"+curso.getIdCurso());

        return mav;
    }

    
    
    
}



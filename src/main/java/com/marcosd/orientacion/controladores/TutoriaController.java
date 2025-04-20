package com.marcosd.orientacion.controladores;


import com.marcosd.orientacion.beans.Tutoria;
import com.marcosd.orientacion.beans.Curso;
import com.marcosd.orientacion.beans.Orientacion;
import com.marcosd.orientacion.repositorio.CursoRepository;
import com.marcosd.orientacion.repositorio.TutoriaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/tutoria")
@RequiredArgsConstructor
public class TutoriaController {

    @Autowired
	private TutoriaRepository tutoriaRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    

    @GetMapping()
    public String listarTutorias(Model model) {
        model.addAttribute("tutorias", tutoriaRepository.findAll());
        return "tutoria/tutorias";
    }
    
    
    
    
    @GetMapping("/{id}")
    public ModelAndView getTutorias(@PathVariable("id") Long id) {
        
    	ModelAndView modelAndView = new ModelAndView("tutoria/tutoria");
    	
    	Tutoria tutoria = tutoriaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    	modelAndView.addObject("tutoria", tutoria);
    	
        return modelAndView;
    }

    
    

    @PostMapping("/add/{idCurso}")
    public String guardarTutoria(@Valid @ModelAttribute("tutoria") Tutoria tutoria,@PathVariable("idCurso") Long idCurso ,BindingResult result) {
    	
        if (result.hasErrors()) {
            return "redirect:/curso/"+idCurso;
        }
        
        
        // Aquí podrías guardar el objeto 'curso' en la base de datos
    	Optional<Curso> cursoOptional = cursoRepository.findById(idCurso);
    	
    	if(cursoOptional.isPresent()) {
    		tutoria.setCurso(cursoOptional.get());
    		tutoriaRepository.save(tutoria);  // Llamada al servicio para guardar
    	}
        
        return "redirect:/curso/"+idCurso;
    }

    
    
    @GetMapping("/del/{id}")
    public String eliminarTutoria(@PathVariable("id") Long idTutoria) {
        
    	Optional<Tutoria> tutoria = tutoriaRepository.findById(idTutoria);
        if (tutoria.isPresent()) {
            Long idCurso = tutoria.get().getCurso().getIdCurso();
            tutoriaRepository.deleteById(idTutoria);
            return "redirect:/curso/" + idCurso;
        }
        return "redirect:/orientaciones";
    }
}


package com.marcosd.orientacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marcosd.orientacion.beans.Orientacion;
import com.marcosd.orientacion.repositorio.OrientacionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/orientacion")
public class OrientacionController {

	@Autowired
	private OrientacionRepository or;
	
	@GetMapping
	public ModelAndView getAll() {
		
		ModelAndView maw = new ModelAndView("/orientacion/orientaciones"); 
		maw.addObject("listado", or.findAll());
		
		return maw;
	}
	
	
    // Método GET para mostrar el formulario
    @GetMapping("/nuevo")
    public ModelAndView showNuevoForm() {
        ModelAndView modelAndView = new ModelAndView("orientacion/orientacionForm");
        modelAndView.addObject("orientacion", new Orientacion()); // Añadimos un objeto vacío al modelo
        return modelAndView;
    }

    // Método POST para recibir el formulario y guardarlo en la base de datos
    @PostMapping("/nuevo")
    public ModelAndView saveOrientacion(@Valid @ModelAttribute("orientacion") Orientacion orientacion, BindingResult result) {
        
    	if (result.hasErrors()) {
            return new ModelAndView("orientacion/orientacionForm");
        }
        // Aquí podrías guardar el objeto 'orientacion' en la base de datos
        or.save(orientacion);  // Llamada al servicio para guardar

        // Redirige a una vista de confirmación o lista de orientaciones
        ModelAndView modelAndView = new ModelAndView("redirect:/orientacion");
        return modelAndView;
    }	
	
}

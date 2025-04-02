package com.marcosd.orientacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marcosd.orientacion.repositorio.OrientacionRepository;

@Controller
@RequestMapping("/orientacion")
public class OrientacionController {

	@Autowired
	private OrientacionRepository or;
	
	@GetMapping
	public ModelAndView getAll() {
		
		ModelAndView maw = new ModelAndView("/orientacion/orientacion"); 
		maw.addObject("listado", or.findAll());
		
		return maw;
	}
	
}

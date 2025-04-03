package com.marcosd.orientacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marcosd.orientacion.beans.Orientacion;
import com.marcosd.orientacion.repositorio.OrientacionRepository;

@Controller
public class IndexController {

	@GetMapping
	public ModelAndView getAll() {
		
		ModelAndView maw = new ModelAndView("index"); 
		
		return maw;
	}
	
	
}

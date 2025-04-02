package com.marcosd.orientacion.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orientacion")
public class OrientacioController {

	@GetMapping
	public String getAll() {
		
		return "/orientacion/orientacion";
	}
	
}

package com.marcosd.orientacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.marcosd.orientacion.beans.Orientacion;
import com.marcosd.orientacion.repositorio.OrientacionRepository;
import com.marcosd.orientacion.service.MailgunEmailService;

@Controller
public class IndexController {

	@Autowired
	MailgunEmailService emailService;


	@GetMapping
	public ModelAndView getAll() {
		
		ModelAndView maw = new ModelAndView("index"); 
		
		return maw;
	}
	
	@GetMapping("/email")
	@ResponseBody
	public String email() {
		
		//String que = emailService.enviarCorreo("marcosd@educastur.org", "prueba", "hola");
		
		return emailService.enviarCorreo();
	}
	
}

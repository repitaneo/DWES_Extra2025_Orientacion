package com.marcosd.orientacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.marcosd.orientacion.beans.Actividad;
import com.marcosd.orientacion.repositorio.ActividadRepository;
import com.marcosd.orientacion.service.ActividadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/configuracion")
@RequiredArgsConstructor
@Log4j2
public class ConfiguracionController {


	@Autowired
    private ActividadService actividadService;


    @GetMapping
    public ModelAndView listarActividades() {
    
        ModelAndView mav = new ModelAndView("configuracion");
        
        return mav;
    }
    
    
    @GetMapping("/actividades/{cantidad}")
    public ModelAndView mostrarActividades(@PathVariable int cantidad) {
        
    	actividadService.setCantidad(cantidad);
        
        ModelAndView mav = new ModelAndView("redirect:/actividad?page=0");
        
        return mav;
    }

  
}
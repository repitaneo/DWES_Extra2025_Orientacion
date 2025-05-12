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
@RequestMapping("/actividad")
@RequiredArgsConstructor
@Log4j2
public class ActividadController {

	@Autowired
    private ActividadRepository actividadRepository;

	@Autowired
    private ActividadService actividadService;


    @GetMapping
    public ModelAndView listarActividades(@RequestParam int page) {
    
    	Page<Actividad> pagina = actividadService.pagina(page);
    
        ModelAndView mav = new ModelAndView("actividades/actividades");
        mav.addObject("actividades", pagina);
        mav.addObject("actividad", new Actividad());
        mav.addObject("page", pagina);
        
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView verActividad(@PathVariable long id) {
    
        ModelAndView mav = new ModelAndView("actividades/actividad");
        mav.addObject("actividad", actividadRepository.findById(id).orElse(null));
        
        return mav;
    }

    @GetMapping("/del/{id}")
    public String eliminarActividad(@PathVariable long id) {
    
        actividadRepository.deleteById(id);
        return "redirect:/actividad?page=0";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editarActividad(@PathVariable long id) {

        ModelAndView mav = new ModelAndView("actividades/actividadForm");
        mav.addObject("actividad", actividadRepository.findById(id).orElse(null));
        return mav;
    }

    @PostMapping
    public String guardarActividad(@ModelAttribute Actividad actividad) {
        
        actividadRepository.save(actividad);
        return "redirect:/actividad?page=0";
    }
}
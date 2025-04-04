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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marcosd.orientacion.beans.Orientacion;
import com.marcosd.orientacion.repositorio.OrientacionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/orientacion")
public class OrientacionController {

	@Autowired
	private OrientacionRepository or;
	
	@GetMapping
    public ModelAndView listarOrientaciones() {
        ModelAndView mav = new ModelAndView("orientacion/orientaciones");
        
        List<Orientacion> orientaciones = or.findByEliminadoFalse();
        mav.addObject("listado", orientaciones);
        return mav;
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
    
    
    
    // Eliminar una orientación (eliminación lógica)
    @GetMapping("/eliminar/{id}")
    public String eliminarOrientacion(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        Optional<Orientacion> optionalOrientacion = or.findById(id);
        if (optionalOrientacion.isPresent()) {
            Orientacion orientacion = optionalOrientacion.get();
            orientacion.setEliminado(true);
            or.save(orientacion);
            
            redirectAttributes.addFlashAttribute("idBorrado", orientacion.getIdOrientacion());
        }
        else redirectAttributes.addFlashAttribute("idBorrado", 0);

        return "redirect:/orientacion";
    }
    
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarOrientacion(@PathVariable Long id) {
        
    	Optional<Orientacion> optionalOrientacion = or.findById(id);
        if (optionalOrientacion.isEmpty() || optionalOrientacion.get().isEliminado()) {
            return new ModelAndView("redirect:/orientacion"); // Si está eliminado, redirige a la lista
        }
        
        ModelAndView mav = new ModelAndView("orientacion/orientacionForm");
        mav.addObject("orientacion", optionalOrientacion.get());
        return mav;
    }
    
    
    
    @GetMapping("/deshacer/{id}")
    public String deshacerEliminacion(@PathVariable Long id) {
        Orientacion orientacion = or.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Orientacion no encontrada con ID " + id));

        // Marcamos el campo eliminado como falso
        orientacion.setEliminado(false);
        or.save(orientacion);

        // Redirigimos a la lista de orientaciones con la eliminación deshecha
        return "redirect:/orientacion";
    }
	
}

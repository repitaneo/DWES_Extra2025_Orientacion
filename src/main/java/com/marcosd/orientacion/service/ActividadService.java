package com.marcosd.orientacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.marcosd.orientacion.beans.Actividad;
import com.marcosd.orientacion.repositorio.ActividadRepository;

@Service
public class ActividadService {


		private int cantidad = 5;

	 @Autowired
	  private ActividadRepository repository;

	    public Page<Actividad> pagina(int pagina) {
	    
	        return repository.findAll(PageRequest.of(pagina,cantidad));
	    }


		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
}

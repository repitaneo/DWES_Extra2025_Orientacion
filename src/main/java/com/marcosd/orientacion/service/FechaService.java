package com.marcosd.orientacion.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class FechaService {

    public int getAnioLectivoActual() {
        LocalDate fechaActual = LocalDate.now();
        int anio = fechaActual.getYear();
        int mes = fechaActual.getMonthValue();

        if (mes >= 1 && mes <= 8) {
            return anio - 1;
        } else {
            return anio;
        }
    }
}
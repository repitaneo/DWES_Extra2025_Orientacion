package com.marcosd.orientacion.beans;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "centros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Centro {

    @Id
    @Column(name = "idcentro")
    private String idCentro;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "email")
    private String email;
    
    
    @OneToOne(mappedBy = "centro")
    @ToString.Exclude
    private Orientacion orientacion;


}

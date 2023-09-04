package com.dagnerchuman.springbootmicroservice3apigateway.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="negocio")
public class Negocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name="direccion", length = 100, nullable = false)
    private String direccion;

    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
}

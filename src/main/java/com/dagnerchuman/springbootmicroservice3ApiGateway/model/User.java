package com.dagnerchuman.springbootmicroservice3ApiGateway.model;

import lombok.Data;

import javax.persistence.*;
import java.util.regex.Pattern;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name="apellido", nullable = false)
    private String apellido;

    @Column(name="telefono", nullable = false)
    private String telefono;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @Transient
    private String token;

    @Column(name = "negocio", nullable = false)
    private Long negocioId;

    @Column(name="tipoDoc", nullable = false)
    private String tipoDoc;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;


    @Column(name="foto", length = 1200, nullable = true )
    private String picture;

    @Column(name="departamento", nullable = false)
    private String departamento;

    @Column(name="provincia", nullable = false)
    private String provincia;

    @Column(name="distrito", nullable = false)
    private String distrito;


}

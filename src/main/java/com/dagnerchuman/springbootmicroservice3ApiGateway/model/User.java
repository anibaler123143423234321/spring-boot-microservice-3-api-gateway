package com.dagnerchuman.springbootmicroservice3ApiGateway.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="apellido", nullable = false)
    private String apellido;

    @Column(name="telefono", nullable = false)
    private String telefono;

    @Column(name="email", nullable = false)
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

    @Column(name = "dni", nullable = false)
    @NotNull
    @Min(value = 10000000, message = "DNI must be an 8-digit number")
    @Digits(integer = 8, fraction = 0, message = "DNI must be an 8-digit number")
    private int dni;

}

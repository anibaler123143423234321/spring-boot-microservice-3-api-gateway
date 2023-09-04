package com.dagnerchuman.springbootmicroservice3apigateway.repository;

import com.dagnerchuman.springbootmicroservice3apigateway.model.Negocio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegocioRepository extends JpaRepository<Negocio, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
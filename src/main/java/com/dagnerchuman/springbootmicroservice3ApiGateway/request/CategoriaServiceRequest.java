package com.dagnerchuman.springbootmicroservice3ApiGateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(
        value = "categoria-service", // Nombre del servicio de categoría
        //path = "/api/categoria", // Ruta del servicio de categoría
        url="${categoria.service.url}",
        configuration = FeignConfiguration.class
)
public interface CategoriaServiceRequest {

    @PostMapping
    Object saveCategoria(@RequestBody Object requestBody);

    @GetMapping
    List<Object> findAllCategorias();

    @GetMapping("/{categoriaId}")
    Object findCategoriaById(@PathVariable("categoriaId") Long categoriaId);

    @PutMapping("/{categoriaId}")
    Object updateCategoria(@PathVariable("categoriaId") Long categoriaId, @RequestBody Object requestBody);

    @DeleteMapping("/{categoriaId}")
    void deleteCategoria(@PathVariable("categoriaId") Long categoriaId);

    // Otros métodos relacionados con la gestión de categorías
}

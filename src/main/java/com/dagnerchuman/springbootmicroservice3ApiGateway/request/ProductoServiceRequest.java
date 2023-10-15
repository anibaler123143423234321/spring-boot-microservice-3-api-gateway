package com.dagnerchuman.springbootmicroservice3ApiGateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value="producto-service",
        //path="/api/producto",
        url="${producto.service.url}",
        configuration = FeignConfiguration.class
)

public interface ProductoServiceRequest {

    @PostMapping
    Object saveProducto(@RequestBody Object requestBody);

    @DeleteMapping("/{productoId}")
    void deleteProducto(@PathVariable("productoId") Long productoId);

    @GetMapping()
    List<Object> getAllProductos();

    @GetMapping("/{productoId}")
    Object getProductoById(@RequestParam("productoId") Long productoId);

    // Agregar el nuevo método para eliminar todos los productos
    @DeleteMapping("/eliminar-todos")
    void deleteAllProductos();

    // Agregar el nuevo método para obtener los siguientes productos
    @GetMapping("/siguientes")
    List<Object> getSiguientesProductos(
            @RequestParam("posicion") int posicion,
            @RequestParam("cantidad") int cantidad
    );

    // Nuevo método para actualizar un producto

    @PutMapping("{productoId}")
    Object actualizarProducto(
            @PathVariable("productoId") Long productoId,
            @RequestBody Object nuevoProducto
    );

}

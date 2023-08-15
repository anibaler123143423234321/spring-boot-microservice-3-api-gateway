package com.dagnerchuman.springbootmicroservice3apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value="producto-service",
        path="/api/producto",
        //url="${producto.service.url}",
        configuration = FeignConfiguration.class
)
public interface ProductoServiceRequest {

    @PostMapping
    Object saveProducto(@RequestBody Object requestBody);

    @DeleteMapping("{productoId}")
    void deleteProducto(@PathVariable("productoId") Long productoId);

    @GetMapping()
    List<Object> getAllProductos();

    @GetMapping("{productoId}")
    Object getProductoById(@PathVariable("productoId") Long productoId);
}

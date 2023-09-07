package com.dagnerchuman.springbootmicroservice3apigateway.controller;

import com.dagnerchuman.springbootmicroservice3apigateway.request.ProductoServiceRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateway/producto")
public class ProductoController {

    @Autowired
    private ProductoServiceRequest productoServiceRequest;

    @PostMapping
    public ResponseEntity<?> saveProducto(@RequestBody Object producto)
    {
        return new ResponseEntity<>(productoServiceRequest.saveProducto(producto), HttpStatus.CREATED);
    }

    @DeleteMapping("{productoId}")
    public ResponseEntity<?> deleteProducto(@PathVariable("productoId") Long productoId)
    {
        productoServiceRequest.deleteProducto(productoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllProductos()
    {
        return ResponseEntity.ok(productoServiceRequest.getAllProductos());
    }



    @GetMapping("{productoId}")
    public ResponseEntity<?> getProductoById(@PathVariable Long productoId) {
        try {
            Object producto = productoServiceRequest.getProductoById(productoId);
            return ResponseEntity.ok(producto);
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Nuevo endpoint para eliminar todos los productos
    @DeleteMapping("/eliminar-todos")
    public ResponseEntity<?> deleteAllProductos() {
        productoServiceRequest.deleteAllProductos();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

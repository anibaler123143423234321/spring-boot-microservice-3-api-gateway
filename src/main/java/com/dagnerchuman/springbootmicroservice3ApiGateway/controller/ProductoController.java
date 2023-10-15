package com.dagnerchuman.springbootmicroservice3ApiGateway.controller;

import com.dagnerchuman.springbootmicroservice3ApiGateway.request.ProductoServiceRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("gateway/producto")
@CrossOrigin(origins = "http://api-gateway:5200") // Esto permite solicitudes desde http://localhost:5200
public class ProductoController {

    @Autowired
    private ProductoServiceRequest productoServiceRequest;

    @PostMapping
    public ResponseEntity<?> saveProducto(@RequestBody Object producto)
    {
        return new ResponseEntity<>(productoServiceRequest.saveProducto(producto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{productoId}")
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

    // Nuevo endpoint para obtener productos según la posición y la cantidad
    @GetMapping("/siguientes")
    public ResponseEntity<?> getSiguientesProductos(
            @RequestParam("posicion") int posicion,
            @RequestParam("cantidad") int cantidad
    ) {
        try {
            Object productos = productoServiceRequest.getSiguientesProductos(posicion, cantidad);
            return ResponseEntity.ok(productos);
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("{productoId}")
    public ResponseEntity<?> actualizarProducto(@PathVariable("productoId") Long productoId, @RequestBody Object nuevoProducto) {
        try {
            Object productoActualizado = productoServiceRequest.actualizarProducto(productoId, nuevoProducto);
            return ResponseEntity.ok(productoActualizado);
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

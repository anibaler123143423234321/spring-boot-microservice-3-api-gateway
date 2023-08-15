package com.dagnerchuman.springbootmicroservice3apigateway.controller;

import com.dagnerchuman.springbootmicroservice3apigateway.request.ProductoServiceRequest;
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


}

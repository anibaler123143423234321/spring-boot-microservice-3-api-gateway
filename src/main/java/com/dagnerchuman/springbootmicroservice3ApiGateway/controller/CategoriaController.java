package com.dagnerchuman.springbootmicroservice3ApiGateway.controller;

import com.dagnerchuman.springbootmicroservice3ApiGateway.request.CategoriaServiceRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaServiceRequest categoriaServiceRequest;

    @PostMapping
    public ResponseEntity<?> saveCategoria(@RequestBody Object categoria) {
        return new ResponseEntity<>(categoriaServiceRequest.saveCategoria(categoria), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategorias() {
        return ResponseEntity.ok(categoriaServiceRequest.findAllCategorias());
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<?> getCategoriaById(@PathVariable Long categoriaId) {
        try {
            Object categoria = categoriaServiceRequest.findCategoriaById(categoriaId);
            return ResponseEntity.ok(categoria);
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<?> updateCategoria(
            @PathVariable Long categoriaId,
            @RequestBody Object categoria) {
        try {
            Object existingCategoria = categoriaServiceRequest.findCategoriaById(categoriaId);
            return ResponseEntity.ok(categoriaServiceRequest.updateCategoria(categoriaId, categoria));
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long categoriaId) {
        try {
            categoriaServiceRequest.deleteCategoria(categoriaId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

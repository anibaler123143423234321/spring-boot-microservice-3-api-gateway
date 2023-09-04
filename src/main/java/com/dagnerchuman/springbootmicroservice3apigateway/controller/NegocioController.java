package com.dagnerchuman.springbootmicroservice3apigateway.controller;

import com.dagnerchuman.springbootmicroservice3apigateway.model.Negocio;
import com.dagnerchuman.springbootmicroservice3apigateway.service.NegocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/negocios")
public class NegocioController {

    private final NegocioService negocioService;

    @Autowired
    public NegocioController(NegocioService negocioService) {
        this.negocioService = negocioService;
    }

    @GetMapping("/")
    public List<Negocio> getAllNegocios() {
        return negocioService.getAllNegocios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Negocio> getNegocioById(@PathVariable Long id) {
        Negocio negocio = negocioService.getNegocioById(id);
        if (negocio != null) {
            return new ResponseEntity<>(negocio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Negocio> createNegocio(@RequestBody Negocio negocio) {
        Negocio createdNegocio = negocioService.createNegocio(negocio);
        return new ResponseEntity<>(createdNegocio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Negocio> updateNegocio(@PathVariable Long id, @RequestBody Negocio negocio) {
        Negocio updatedNegocio = negocioService.updateNegocio(id, negocio);
        if (updatedNegocio != null) {
            return new ResponseEntity<>(updatedNegocio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNegocio(@PathVariable Long id) {
        negocioService.deleteNegocio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

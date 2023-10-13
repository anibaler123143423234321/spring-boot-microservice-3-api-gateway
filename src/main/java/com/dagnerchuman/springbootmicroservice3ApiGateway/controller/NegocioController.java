package com.dagnerchuman.springbootmicroservice3ApiGateway.controller;

import com.dagnerchuman.springbootmicroservice3ApiGateway.request.NegocioServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gateway/negocios")
public class NegocioController {

    @Autowired
    private NegocioServiceRequest negocioServiceRequest;

    @PostMapping("/")
    public ResponseEntity<Object> saveNegocio(@RequestBody Object negocio) {
        return new ResponseEntity<>(negocioServiceRequest.saveNegocio(negocio), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNegocioById(@PathVariable Long id) {
        Object negocio = negocioServiceRequest.getNegocioById(id);
        if (negocio != null) {
            return new ResponseEntity<>(negocio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Object>> getAllNegocios() {
        List<Object> negocios = negocioServiceRequest.getAllNegocios();
        return new ResponseEntity<>(negocios, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateNegocio(@PathVariable Long id, @RequestBody Object negocio) {
        Object updatedNegocio = negocioServiceRequest.updateNegocio(id, negocio);
        if (updatedNegocio != null) {
            return new ResponseEntity<>(updatedNegocio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNegocio(@PathVariable Long id) {
        negocioServiceRequest.deleteNegocio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

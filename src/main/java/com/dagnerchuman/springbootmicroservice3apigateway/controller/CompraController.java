package com.dagnerchuman.springbootmicroservice3apigateway.controller;

import com.dagnerchuman.springbootmicroservice3apigateway.request.CompraServiceRequest;
import com.dagnerchuman.springbootmicroservice3apigateway.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateway/compra")
@CrossOrigin(origins = "http://localhost:5200") // Esto permite solicitudes desde http://localhost:5200
public class CompraController {

    @Autowired
    private CompraServiceRequest compraServiceRequest;

    @PostMapping
    public ResponseEntity<?> saveCompra(@RequestBody Object compra)
    {
        return new ResponseEntity<>(compraServiceRequest.saveCompra(compra), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllComprasOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return ResponseEntity.ok(compraServiceRequest.getAllComprasOfUser(userPrincipal.getId()));
    }

    @PutMapping("{compraId}")
    public ResponseEntity<?> updateCompra(@PathVariable Long compraId, @RequestBody Object compra) {
        return ResponseEntity.ok(compraServiceRequest.updateCompra(compraId, compra));
    }
}

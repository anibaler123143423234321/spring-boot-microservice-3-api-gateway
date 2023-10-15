package com.dagnerchuman.springbootmicroservice3ApiGateway.controller;

import com.dagnerchuman.springbootmicroservice3ApiGateway.request.CompraServiceRequest;
import com.dagnerchuman.springbootmicroservice3ApiGateway.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateway/compra")
@CrossOrigin(origins = "http://api-gateway:5200") // Esto permite solicitudes desde http://localhost:5200
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

    // Agrega este nuevo método para listar todas las compras
    @GetMapping("/all")
    public ResponseEntity<?> getAllCompras() {
        return ResponseEntity.ok(compraServiceRequest.getAllCompras());
    }


}

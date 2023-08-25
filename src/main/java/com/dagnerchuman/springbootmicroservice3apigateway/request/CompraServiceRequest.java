package com.dagnerchuman.springbootmicroservice3apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value="compra-service",
        path="api/compra",
        //url="${compras.service.url}",
        configuration = FeignConfiguration.class
)
public interface CompraServiceRequest {

    @PostMapping
    Object saveCompra(@RequestBody Object requestBody);

    @GetMapping("{userId}")
    List<Object> getAllComprasOfUser(@PathVariable("userId") Long userId);

    @PutMapping("{compraId}")
    Object updateCompra(
            @PathVariable("compraId") Long compraId,
            @RequestBody Object compra
    );
}

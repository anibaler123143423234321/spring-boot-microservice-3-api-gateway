package com.dagnerchuman.springbootmicroservice3ApiGateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value="negocio-service",
        //path="api/negocios",
        url="${negocio.service.url}",
        configuration = FeignConfiguration.class
)
public interface NegocioServiceRequest {

    @PostMapping("/")
    Object saveNegocio(@RequestBody Object requestBody);

    @GetMapping("/{id}")
    Object getNegocioById(@PathVariable("id") Long id);

    @GetMapping("/")
    List<Object> getAllNegocios();

    @PutMapping("/{id}")
    Object updateNegocio(@PathVariable("id") Long id, @RequestBody Object negocio);

    @DeleteMapping("/{id}")
    void deleteNegocio(@PathVariable("id") Long id);

}

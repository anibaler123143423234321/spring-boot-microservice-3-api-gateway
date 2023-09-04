package com.dagnerchuman.springbootmicroservice3apigateway.controller;

import com.dagnerchuman.springbootmicroservice3apigateway.model.User;
import com.dagnerchuman.springbootmicroservice3apigateway.model.Negocio;
import com.dagnerchuman.springbootmicroservice3apigateway.repository.NegocioRepository;
import com.dagnerchuman.springbootmicroservice3apigateway.service.AuthenticationService;
import com.dagnerchuman.springbootmicroservice3apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private NegocioRepository negocioRepository;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>("El nombre de usuario ya está en uso.", HttpStatus.CONFLICT);
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>("El correo electrónico ya está en uso.", HttpStatus.CONFLICT);
        }

// Obtén el negocio por su ID
        Long negocioId = user.getNegocio().getId();
        Negocio negocio = negocioRepository.findById(negocioId)
                .orElseThrow(() -> new IllegalArgumentException("El negocio con ID " + negocioId + " no existe."));

        // Crea el usuario y establece la relación con el negocio
        User userc = new User();
        userc.setNombre(user.getNombre());
        userc.setApellido(user.getApellido());
        userc.setTelefono(user.getTelefono());
        userc.setEmail(user.getEmail());
        userc.setUsername(user.getUsername());
        userc.setPassword(user.getPassword());
        userc.setNegocio(negocio);

        User savedUser = userService.saveUser(userc);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user)
    {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }


}

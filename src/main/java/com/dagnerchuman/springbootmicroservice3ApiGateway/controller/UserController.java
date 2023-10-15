package com.dagnerchuman.springbootmicroservice3ApiGateway.controller;

import com.dagnerchuman.springbootmicroservice3ApiGateway.model.Role;
import com.dagnerchuman.springbootmicroservice3ApiGateway.model.User;
import com.dagnerchuman.springbootmicroservice3ApiGateway.security.UserPrincipal;
import com.dagnerchuman.springbootmicroservice3ApiGateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://api-gateway:5200") // Esto permite solicitudes desde http://localhost:5200
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("listar")
    public List<User> listUsers() {
        return userService.findAllUsers();
    }


    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role) {
        User user = userService.findByUsername(userPrincipal.getUsername()).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (role == Role.SUPERADMIN && user.getRole() == Role.ADMIN) {
            // Actualiza el rol a SUPERADMIN si es un ADMIN
            userService.changeRole(Role.SUPERADMIN, userPrincipal.getUsername());
            return ResponseEntity.ok(true);
        } else {
            // Maneja otros cambios de roles aquí si es necesario
            userService.changeRole(role, userPrincipal.getUsername());
            return ResponseEntity.ok(true);
        }
    }


    @GetMapping()
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return new ResponseEntity<>(userService.findByUsernameReturnToken(userPrincipal.getUsername()), HttpStatus.OK);
    }

    // http://locahost:5555/gateway/usuario/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long userId) {
        try {
            User usuario = userService.findUserById(userId);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("deleteAll")
    public ResponseEntity<?> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok("Todos los usuarios han sido eliminados.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el user: " + e.getMessage());
        }
    }

}

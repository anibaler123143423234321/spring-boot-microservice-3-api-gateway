package com.dagnerchuman.springbootmicroservice3apigateway.controller;

import com.dagnerchuman.springbootmicroservice3apigateway.model.Role;
import com.dagnerchuman.springbootmicroservice3apigateway.model.User;
import com.dagnerchuman.springbootmicroservice3apigateway.security.UserPrincipal;
import com.dagnerchuman.springbootmicroservice3apigateway.service.UserService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("listar")
    public List<User> listUsers() {
        return userService.findAllUsers();
    }

    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role)
    {
        userService.changeRole(role, userPrincipal.getUsername());

        return ResponseEntity.ok(true);
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

}

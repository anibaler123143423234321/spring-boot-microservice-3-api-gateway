package com.dagnerchuman.springbootmicroservice3ApiGateway.service;

import com.dagnerchuman.springbootmicroservice3ApiGateway.model.Role;
import com.dagnerchuman.springbootmicroservice3ApiGateway.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> findAllUsers();
    User saveUser(User user);


    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void changeRole(Role newRole, String username);

    User findByUsernameReturnToken(String username);

    User findUserById(Long userId);

    void deleteAllUsers();


    void deleteUserByUsername(String username); // Agrega este método

    User updateUser(Long id, User user);
}

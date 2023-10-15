package com.dagnerchuman.springbootmicroservice3ApiGateway.repository;

import com.dagnerchuman.springbootmicroservice3ApiGateway.model.Role;
import com.dagnerchuman.springbootmicroservice3ApiGateway.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //findBy + nombreCampo
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    @Modifying
    @Query("update User set role=:role where username=:username")
    void updateUserRole(@Param("username") String username, @Param("role") Role role);



}

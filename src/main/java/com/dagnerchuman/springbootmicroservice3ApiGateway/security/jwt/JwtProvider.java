package com.dagnerchuman.springbootmicroservice3ApiGateway.security.jwt;

import com.dagnerchuman.springbootmicroservice3ApiGateway.model.User;
import com.dagnerchuman.springbootmicroservice3ApiGateway.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {

    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    String generateToken(User user);

    boolean isTokenValid(HttpServletRequest request);
}

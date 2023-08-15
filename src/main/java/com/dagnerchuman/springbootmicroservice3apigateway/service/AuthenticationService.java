package com.dagnerchuman.springbootmicroservice3apigateway.service;

import com.dagnerchuman.springbootmicroservice3apigateway.model.User;

public interface AuthenticationService {

    User signInAndReturnJWT(User signInRequest);

}

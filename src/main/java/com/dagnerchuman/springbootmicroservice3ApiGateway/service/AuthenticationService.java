package com.dagnerchuman.springbootmicroservice3ApiGateway.service;

import com.dagnerchuman.springbootmicroservice3ApiGateway.model.User;

public interface AuthenticationService {

    User signInAndReturnJWT(User signInRequest);

}

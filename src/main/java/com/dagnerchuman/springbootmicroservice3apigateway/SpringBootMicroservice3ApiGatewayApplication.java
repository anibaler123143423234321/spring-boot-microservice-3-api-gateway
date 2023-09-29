package com.dagnerchuman.springbootmicroservice3apigateway;

import com.dagnerchuman.springbootmicroservice3apigateway.model.Role;
import com.dagnerchuman.springbootmicroservice3apigateway.model.User;
import com.dagnerchuman.springbootmicroservice3apigateway.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;


@EnableFeignClients
@SpringBootApplication
public class SpringBootMicroservice3ApiGatewayApplication {

	@Bean
	public PasswordEncoder 	passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservice3ApiGatewayApplication.class, args);
	}


}

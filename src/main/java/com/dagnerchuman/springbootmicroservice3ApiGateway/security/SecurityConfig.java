package com.dagnerchuman.springbootmicroservice3ApiGateway.security;

import com.dagnerchuman.springbootmicroservice3ApiGateway.model.Role;
//import com.dagnerchuman.springbootmicroservice3apigateway.security.jwt.JwtAuthorizationFilter;
import com.dagnerchuman.springbootmicroservice3ApiGateway.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception
    {
        AuthenticationManagerBuilder auth = http.getSharedObject(
                AuthenticationManagerBuilder.class
        );

        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

        AuthenticationManager authenticationManager = auth.build();
        http.cors();
        http.csrf().disable();
        http.authenticationManager(authenticationManager);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.authorizeHttpRequests()
                .antMatchers("/api/authentication/sign-in", "/api/authentication/sign-up", "gateway/producto/siguientes", "/reniec/getPersonInfoByDNI").permitAll()
                .antMatchers(HttpMethod.GET, "/gateway/compra").hasAnyRole(Role.USER.name(),Role.ADMIN.name(), Role.SUPERADMIN.name())
                .antMatchers(HttpMethod.GET, "/gateway/compra/all").hasAnyRole(Role.USER.name(),Role.ADMIN.name(), Role.SUPERADMIN.name())
                .antMatchers(HttpMethod.GET, "/gateway/negocios/").permitAll()
                .antMatchers(HttpMethod.GET, "/gateway/producto/{productoId}").hasAnyRole(Role.USER.name(),Role.ADMIN.name(), Role.SUPERADMIN.name())
                .antMatchers(HttpMethod.POST, "/gateway/compra").hasAnyRole(Role.USER.name(),Role.ADMIN.name(), Role.SUPERADMIN.name())
                .antMatchers(HttpMethod.PUT, "/gateway/compra").hasAnyRole(Role.USER.name(),Role.ADMIN.name(), Role.SUPERADMIN.name())
                .antMatchers(HttpMethod.POST, "/gateway/compra").hasAnyRole(Role.USER.name(),Role.ADMIN.name(), Role.SUPERADMIN.name())


                .antMatchers(HttpMethod.GET, "/gateway/producto").hasAnyRole(Role.USER.name(),Role.ADMIN.name(), Role.SUPERADMIN.name())
                .antMatchers(HttpMethod.PUT, "/gateway/producto/{productoId}").hasAnyRole(Role.USER.name(),Role.ADMIN.name(), Role.SUPERADMIN.name())
                .antMatchers(HttpMethod.GET, "/gateway/categoria/}").hasAnyRole(Role.USER.name())

                .antMatchers("/gateway/producto/**").hasAnyRole(Role.ADMIN.name(), Role.SUPERADMIN.name())
                //.antMatchers(HttpMethod.PUT, "/api/user/{id}").permitAll()
                .antMatchers("/gateway/negocios/**").hasAnyRole(Role.ADMIN.name(), Role.SUPERADMIN.name())
                .antMatchers("/gateway/categoria/**").hasAnyRole(Role.ADMIN.name(), Role.SUPERADMIN.name())
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}

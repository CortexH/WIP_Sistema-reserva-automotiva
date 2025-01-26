package com.example.SistemaReservaAutomotiva.security;

import com.example.SistemaReservaAutomotiva.configurations.EndpointWhitelist;
import com.example.SistemaReservaAutomotiva.dto.output.GenericErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    throws Exception{

        return httpSecurity
                .cors(cors -> cors.disable())
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(EndpointWhitelist.NoAuthorizationEndpoints).permitAll()
                        .requestMatchers(EndpointWhitelist.SwaggerEndpoints).permitAll()
                        .requestMatchers(EndpointWhitelist.ForClientRequests).hasAnyRole("CLIENT", "EMPLOYEE", "ADMIN")
                        .requestMatchers(EndpointWhitelist.ForEmployeeEndpoints).hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers(EndpointWhitelist.AdminRequests).hasAnyRole("ADMIN")
                ).exceptionHandling(exp -> exp.accessDeniedHandler((request, response, accessDeniedException) -> {
                    GenericErrorDTO err = new GenericErrorDTO(LocalDateTime.now().toString(), 403, "Prohibited access");
                    response.setContentType("application/json");
                    response.setStatus(403);
                    response.getWriter().write(new ObjectMapper().writeValueAsString(err));
                    response.getWriter().flush();
                }))
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

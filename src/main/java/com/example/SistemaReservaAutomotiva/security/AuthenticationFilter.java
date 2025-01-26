package com.example.SistemaReservaAutomotiva.security;

import com.example.SistemaReservaAutomotiva.configurations.EndpointWhitelist;
import com.example.SistemaReservaAutomotiva.domain.user.User;
import com.example.SistemaReservaAutomotiva.domain.user.UserCustomDetails;
import com.example.SistemaReservaAutomotiva.dto.output.GenericErrorDTO;
import com.example.SistemaReservaAutomotiva.exceptions.BadRequestCustomException;
import com.example.SistemaReservaAutomotiva.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private UserCustomDetails userCustomDetails;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try{
            if(request.getRequestURI().startsWith("/v3/api-docs")
                    || request.getRequestURI().startsWith("/swagger-ui")
                    || request.getRequestURI().startsWith("/h2-console")
                    || request.getRequestURI().startsWith("/favicon")
            ){
                filterChain.doFilter(request, response);
                return;
            }

            if(checkAllowedEndpoint(request.getRequestURI())){
                filterChain.doFilter(request, response);
                return;
            }

            String token = request.getHeader("Authorization");
            String subject = jwtService.getTokenSubject(token);

            User user = userService.getUserByEmail(subject);
            UserDetails details = new UserCustomDetails(user);

            Authentication authentication = new UsernamePasswordAuthenticationToken(subject, null, details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (NoSuchElementException e) {
            response.setStatus(401);
            GenericErrorDTO err = new GenericErrorDTO(LocalDateTime.now().toString(), 401, "Unauthorized access");
            response.setContentType("application/json");

            response.getWriter().write(new ObjectMapper().writeValueAsString(err));
            response.getWriter().flush();

        } catch (DataIntegrityViolationException e) {
            throw new BadRequestCustomException(e.getMessage());
        } catch (ServletException e) {
            response.setStatus(500);
            response.getWriter().write(e.getMessage());
            response.getWriter().flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkAllowedEndpoint(String uri){
        return Arrays.asList(EndpointWhitelist.NoAuthorizationEndpoints).contains(uri);
    }

}

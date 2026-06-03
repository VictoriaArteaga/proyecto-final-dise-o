package com.cultura.narino.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);

        try {
            if (jwtService.esTokenValido(token)) {
                String correo = jwtService.extraerCorreo(token);
                String rol = jwtService.extraerRol(token); // Viene como "ADMINISTRADOR"
                String id = jwtService.extraerId(token);

                if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    String authorityName = rol.startsWith("ROLE_") ? rol : "ROLE_" + rol;

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            correo,
                            null,
                            Collections.singletonList(new SimpleGrantedAuthority(authorityName))
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    request.setAttribute("userId", id);
                    request.setAttribute("userRol", rol);

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            // Loguear el error ayuda a saber por qué falla el token en consola
            System.err.println("Error procesando JWT: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
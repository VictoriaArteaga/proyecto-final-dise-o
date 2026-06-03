package com.cultura.narino.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1. Acceso Público
                        .requestMatchers("/api/auth/registro", "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/articulos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/eventos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/comentarios/articulo/**").permitAll()

                        // 2. Gestión de Artículos (Ahora permite ADMINISTRADOR y visitante)
                        .requestMatchers(HttpMethod.POST, "/api/articulos/**").hasAnyRole("ADMINISTRADOR", "visitante")
                        .requestMatchers(HttpMethod.PUT, "/api/articulos/**").hasAnyRole("ADMINISTRADOR", "visitante")
                        .requestMatchers(HttpMethod.DELETE, "/api/articulos/**").hasAnyRole("ADMINISTRADOR", "visitante")

                        // 3. Gestión de Eventos (Ahora permite ADMINISTRADOR y visitante)
                        .requestMatchers(HttpMethod.POST, "/api/eventos/**").hasAnyRole("ADMINISTRADOR", "visitante")
                        .requestMatchers(HttpMethod.DELETE, "/api/eventos/**").hasAnyRole("ADMINISTRADOR", "visitante")

                        // 4. Gestión de Usuarios (Normalmente esto sí lo dejamos solo para el ADMIN real)
                        .requestMatchers("/api/auth/usuarios/**").hasRole("ADMINISTRADOR")

                        // 5. Usuarios logueados
                        .requestMatchers(HttpMethod.POST, "/api/comentarios/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/comentarios/**").authenticated()
                        .requestMatchers("/api/auth/perfil/**").authenticated()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
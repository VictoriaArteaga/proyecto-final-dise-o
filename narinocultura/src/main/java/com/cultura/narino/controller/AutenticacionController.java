package com.cultura.narino.controller;

import com.cultura.narino.dto.CambiarRolRequest;
import com.cultura.narino.dto.LoginRequest;
import com.cultura.narino.dto.RegistroRequest;
import com.cultura.narino.dto.AuthResponse;
import com.cultura.narino.dto.PerfilResponse;
import com.cultura.narino.dto.UsuarioResumen;
import com.cultura.narino.service.AutenticacionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    @PostMapping("/registro")
    public ResponseEntity<PerfilResponse> registrar(@Valid @RequestBody RegistroRequest req) {
        return ResponseEntity.ok(autenticacionService.registrar(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> iniciarSesion(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(autenticacionService.iniciarSesion(req));
    }

    @DeleteMapping("/usuarios/{userId}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String userId,
                                                HttpServletRequest request) {
        String solicitanteId = (String) request.getAttribute("userId");
        autenticacionService.eliminarUsuario(userId, solicitanteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/perfil/{userId}")
    public ResponseEntity<PerfilResponse> obtenerPerfil(@PathVariable String userId) {
        return ResponseEntity.ok(autenticacionService.obtenerPerfil(userId));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioResumen>> listarUsuarios() {
        return ResponseEntity.ok(autenticacionService.listarUsuarios());
    }

    @PutMapping("/usuarios/{userId}/rol")
    public ResponseEntity<PerfilResponse> cambiarRol(@PathVariable String userId,
                                                     @Valid @RequestBody CambiarRolRequest req) {
        return ResponseEntity.ok(autenticacionService.cambiarRol(userId, req.getRol()));
    }
}
package com.cultura.narino.service;

import com.cultura.narino.dto.LoginRequest;
import com.cultura.narino.dto.RegistroRequest;
import com.cultura.narino.dto.AuthResponse;
import com.cultura.narino.dto.PerfilResponse;
import com.cultura.narino.dto.UsuarioResumen;

import java.util.List;

public interface AutenticacionService {

    PerfilResponse registrar(RegistroRequest req);

    AuthResponse iniciarSesion(LoginRequest req);

    void eliminarUsuario(String userId, String solicitanteId);

    PerfilResponse obtenerPerfil(String userId);

    List<UsuarioResumen> listarUsuarios();

    PerfilResponse cambiarRol(String userId, String nuevoRol);
}

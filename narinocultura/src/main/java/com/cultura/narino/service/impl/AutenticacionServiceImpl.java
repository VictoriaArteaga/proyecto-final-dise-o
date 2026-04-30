package com.cultura.narino.service.impl;

import com.cultura.narino.dto.LoginRequest;
import com.cultura.narino.dto.RegistroRequest;
import com.cultura.narino.dto.AuthResponse;
import com.cultura.narino.dto.PerfilResponse;
import com.cultura.narino.dto.UsuarioResumen;
import com.cultura.narino.exception.BusinessException;
import com.cultura.narino.exception.ResourceNotFoundException;
import com.cultura.narino.exception.UnauthorizedException;
import com.cultura.narino.model.Administrador;
import com.cultura.narino.model.Rol;
import com.cultura.narino.model.Usuario;
import com.cultura.narino.model.Visitante;
import com.cultura.narino.repository.UsuarioRepository;
import com.cultura.narino.security.JwtService;
import com.cultura.narino.service.AutenticacionService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AutenticacionServiceImpl(UsuarioRepository usuarioRepository,
                                    PasswordEncoder passwordEncoder,
                                    JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public PerfilResponse registrar(RegistroRequest req) {
        if (usuarioRepository.existePorCorreo(req.getCorreo())) {
            throw new BusinessException("El correo ya está registrado");
        }

        Visitante nuevo = new Visitante(req.getNombre(), req.getCorreo());
        nuevo.setContrasena(passwordEncoder.encode(req.getContrasena()));

        Usuario guardado = usuarioRepository.save(nuevo);

        return new PerfilResponse(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getCorreo(),
                guardado.getRol().name()
        );
    }

    @Override
    public AuthResponse iniciarSesion(LoginRequest req) {
        Usuario usuario = usuarioRepository.buscarPorCorreo(req.getCorreo())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        if (!passwordEncoder.matches(req.getContrasena(), usuario.getContrasena())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String token = jwtService.generarToken(usuario);

        return new AuthResponse(token, usuario.getNombre(), usuario.getRol().name());
    }

    @Override
    public void eliminarUsuario(String userId, String solicitanteId) {
        Usuario solicitante = usuarioRepository.findById(solicitanteId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario solicitante no encontrado"));

        if (solicitante.getRol() != Rol.ADMINISTRADOR) {
            throw new UnauthorizedException("Solo un administrador puede eliminar usuarios");
        }

        if (!usuarioRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Usuario a eliminar no encontrado");
        }

        usuarioRepository.deleteById(userId);
    }

    @Override
    public PerfilResponse obtenerPerfil(String userId) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return new PerfilResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol().name()
        );
    }

    @Override
    public List<UsuarioResumen> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioResumen(
                        u.getId(),
                        u.getNombre(),
                        u.getCorreo(),
                        u.getRol().name()))
                .collect(Collectors.toList());
    }

    @Override
    public PerfilResponse cambiarRol(String userId, Rol nuevoRol) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Usuario actualizado;
        if (nuevoRol == Rol.ADMINISTRADOR && !(usuario instanceof Administrador)) {
            Administrador admin = new Administrador(usuario.getNombre(), usuario.getCorreo());
            admin.setId(usuario.getId());
            admin.setContrasena(usuario.getContrasena());
            actualizado = admin;
        } else if (nuevoRol == Rol.VISITANTE && !(usuario instanceof Visitante)) {
            Visitante visitante = new Visitante(usuario.getNombre(), usuario.getCorreo());
            visitante.setId(usuario.getId());
            visitante.setContrasena(usuario.getContrasena());
            actualizado = visitante;
        } else {
            usuario.setRol(nuevoRol);
            actualizado = usuario;
        }

        Usuario guardado = usuarioRepository.save(actualizado);

        return new PerfilResponse(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getCorreo(),
                guardado.getRol().name()
        );
    }
}
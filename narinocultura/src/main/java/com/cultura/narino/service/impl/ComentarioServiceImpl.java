package com.cultura.narino.service.impl;

import com.cultura.narino.dto.ComentarioRequest;
import com.cultura.narino.dto.ComentarioResponse;
import com.cultura.narino.exception.ResourceNotFoundException;
import com.cultura.narino.exception.UnauthorizedException;
import com.cultura.narino.model.Comentario;
import com.cultura.narino.model.Usuario;
import com.cultura.narino.repository.ArticuloRepository;
import com.cultura.narino.repository.ComentarioRepository;
import com.cultura.narino.repository.UsuarioRepository;
import com.cultura.narino.service.ComentarioService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository,
                                 ArticuloRepository articuloRepository,
                                 UsuarioRepository usuarioRepository) {
        this.comentarioRepository = comentarioRepository;
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<ComentarioResponse> listarPorArticulo(String articuloId) {
        if (!articuloRepository.existsById(articuloId)) {
            throw new ResourceNotFoundException("Artículo no encontrado");
        }

        List<Comentario> comentarios = comentarioRepository.buscarPorArticulo(articuloId);

        Map<String, String> autores = new HashMap<>();
        for (Comentario c : comentarios) {
            if (!autores.containsKey(c.getAutorId())) {
                usuarioRepository.findById(c.getAutorId())
                        .ifPresent(u -> autores.put(u.getId(), u.getNombre()));
            }
        }

        return comentarios.stream()
                .map(c -> new ComentarioResponse(
                        c.getId(),
                        c.getContenido(),
                        autores.getOrDefault(c.getAutorId(), "Anónimo"),
                        c.getArticuloId(),
                        c.getFechaCreacion()))
                .collect(Collectors.toList());
    }

    @Override
    public ComentarioResponse agregar(ComentarioRequest req, String autorId) {
        if (!articuloRepository.existsById(req.getArticuloId())) {
            throw new ResourceNotFoundException("Artículo no encontrado");
        }

        Usuario autor = usuarioRepository.findById(autorId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario autor no encontrado"));

        Comentario comentario = new Comentario(req.getContenido(), autorId, req.getArticuloId());
        Comentario guardado = comentarioRepository.save(comentario);

        return new ComentarioResponse(
                guardado.getId(),
                guardado.getContenido(),
                autor.getNombre(),
                guardado.getArticuloId(),
                guardado.getFechaCreacion());
    }

    @Override
    public void eliminar(String comentarioId, String solicitanteId, boolean esAdmin) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));

        if (!esAdmin && !comentario.getAutorId().equals(solicitanteId)) {
            throw new UnauthorizedException("No tiene permisos para eliminar este comentario");
        }

        comentarioRepository.deleteById(comentarioId);
    }
}

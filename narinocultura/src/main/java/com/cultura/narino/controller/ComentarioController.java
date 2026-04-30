package com.cultura.narino.controller;

import com.cultura.narino.dto.ComentarioRequest;
import com.cultura.narino.dto.ComentarioResponse;
import com.cultura.narino.service.ComentarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping("/articulo/{articuloId}")
    public ResponseEntity<List<ComentarioResponse>> listarPorArticulo(@PathVariable String articuloId) {
        return ResponseEntity.ok(comentarioService.listarPorArticulo(articuloId));
    }

    @PostMapping
    public ResponseEntity<ComentarioResponse> agregar(@Valid @RequestBody ComentarioRequest req,
                                                      HttpServletRequest request) {
        String autorId = (String) request.getAttribute("userId");
        return ResponseEntity.ok(comentarioService.agregar(req, autorId));
    }

    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<Void> eliminar(@PathVariable String comentarioId,
                                         HttpServletRequest request) {
        String solicitanteId = (String) request.getAttribute("userId");
        String rol = (String) request.getAttribute("userRol");
        boolean esAdmin = "ADMINISTRADOR".equals(rol);

        comentarioService.eliminar(comentarioId, solicitanteId, esAdmin);
        return ResponseEntity.noContent().build();
    }
}

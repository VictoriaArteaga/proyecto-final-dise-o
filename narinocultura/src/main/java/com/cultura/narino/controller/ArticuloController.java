package com.cultura.narino.controller;

import com.cultura.narino.dto.ArticuloRequest;
import com.cultura.narino.dto.ArticuloResponse;
import com.cultura.narino.dto.ArticuloResumen;
import com.cultura.narino.model.CategoriaEnum;
import com.cultura.narino.service.ArticuloService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    private final ArticuloService articuloServicio;

    public ArticuloController(ArticuloService articuloServicio) {
        this.articuloServicio = articuloServicio;
    }

    @GetMapping
    public ResponseEntity<Page<ArticuloResumen>> listar(
            @RequestParam(required = false) CategoriaEnum categoria,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(articuloServicio.listar(categoria, q, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloResponse> detalle(@PathVariable String id) {
        return ResponseEntity.ok(articuloServicio.detalle(id));
    }

    @PostMapping
    public ResponseEntity<ArticuloResponse> crear(@Valid @RequestBody ArticuloRequest req) {
        return ResponseEntity.ok(articuloServicio.crear(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticuloResponse> editar(@PathVariable String id,
                                                   @Valid @RequestBody ArticuloRequest req) {
        return ResponseEntity.ok(articuloServicio.editar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        articuloServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}



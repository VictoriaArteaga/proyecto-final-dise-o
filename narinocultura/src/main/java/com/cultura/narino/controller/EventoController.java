package com.cultura.narino.controller;

import com.cultura.narino.dto.EventoRequest;
import com.cultura.narino.dto.EventoResponse;
import com.cultura.narino.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<Page<EventoResponse>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(eventoService.listar(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> detalle(@PathVariable String id) {
        return ResponseEntity.ok(eventoService.detalle(id));
    }

    @PostMapping
    public ResponseEntity<EventoResponse> crear(@Valid @RequestBody EventoRequest req) {
        return ResponseEntity.ok(eventoService.crear(req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

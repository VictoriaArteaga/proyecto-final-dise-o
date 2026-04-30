package com.cultura.narino.service.impl;

import com.cultura.narino.dto.EventoRequest;
import com.cultura.narino.dto.EventoResponse;
import com.cultura.narino.exception.ResourceNotFoundException;
import com.cultura.narino.model.EventoCultural;
import com.cultura.narino.repository.EventoRepository;
import com.cultura.narino.service.EventoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public Page<EventoResponse> listar(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaEvento").ascending());

        Page<EventoCultural> resultados = eventoRepository.buscarEventosFuturos(new Date(), pageable);

        return resultados.map(e -> new EventoResponse(
                e.getId(),
                e.getTitulo(),
                e.getDescripcion(),
                e.getFechaEvento(),
                e.getLugar(),
                e.getImagenUrl()));
    }

    @Override
    public EventoResponse detalle(String id) {
        EventoCultural e = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));

        return new EventoResponse(
                e.getId(),
                e.getTitulo(),
                e.getDescripcion(),
                e.getFechaEvento(),
                e.getLugar(),
                e.getImagenUrl());
    }

    @Override
    public EventoResponse crear(EventoRequest req) {
        EventoCultural evento = new EventoCultural(
                req.getTitulo(),
                req.getDescripcion(),
                req.getFecha(),
                req.getLugar()
        );
        evento.setImagenUrl(req.getImagenUrl());

        EventoCultural guardado = eventoRepository.save(evento);

        return new EventoResponse(
                guardado.getId(),
                guardado.getTitulo(),
                guardado.getDescripcion(),
                guardado.getFechaEvento(),
                guardado.getLugar(),
                guardado.getImagenUrl());
    }

    @Override
    public void eliminar(String id) {
        if (!eventoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento no encontrado");
        }
        eventoRepository.deleteById(id);
    }
}

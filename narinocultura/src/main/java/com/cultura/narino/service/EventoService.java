package com.cultura.narino.service;

import com.cultura.narino.dto.EventoRequest;
import com.cultura.narino.dto.EventoResponse;
import org.springframework.data.domain.Page;

public interface EventoService {

    Page<EventoResponse> listar(int page, int size);

    EventoResponse detalle(String id);

    EventoResponse crear(EventoRequest req);

    void eliminar(String id);
}

package com.cultura.narino.service;

import com.cultura.narino.dto.ComentarioRequest;
import com.cultura.narino.dto.ComentarioResponse;

import java.util.List;

public interface ComentarioService {

    List<ComentarioResponse> listarPorArticulo(String articuloId);

    ComentarioResponse agregar(ComentarioRequest req, String autorId);

    void eliminar(String comentarioId, String solicitanteId, boolean esAdmin);
}

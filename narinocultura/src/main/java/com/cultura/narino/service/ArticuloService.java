package com.cultura.narino.service;

import com.cultura.narino.dto.ArticuloRequest;
import com.cultura.narino.dto.ArticuloResponse;
import com.cultura.narino.dto.ArticuloResumen;
import com.cultura.narino.model.Categoria;
import org.springframework.data.domain.Page;

public interface ArticuloService {

    Page<ArticuloResumen> listar(Categoria categoria, String q, int page, int size);

    ArticuloResponse detalle(String id);

    ArticuloResponse crear(ArticuloRequest req);

    ArticuloResponse editar(String id, ArticuloRequest req);

    void eliminar(String id);
}

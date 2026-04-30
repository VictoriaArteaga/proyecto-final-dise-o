package com.cultura.narino.service.impl;

import com.cultura.narino.dto.ArticuloRequest;
import com.cultura.narino.dto.ArticuloResponse;
import com.cultura.narino.dto.ArticuloResumen;
import com.cultura.narino.exception.ResourceNotFoundException;
import com.cultura.narino.model.ArticuloCultural;
import com.cultura.narino.model.CategoriaEnum;
import com.cultura.narino.repository.ArticuloRepository;
import com.cultura.narino.service.ArticuloService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final ArticuloRepository articuloRepository;

    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Override
    public Page<ArticuloResumen> listar(CategoriaEnum categoria, String q, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaPublicacion").descending());

        Page<ArticuloCultural> resultados;

        boolean tieneCategoria = categoria != null;
        boolean tieneTexto = q != null && !q.isBlank();

        if (tieneCategoria && tieneTexto) {
            resultados = articuloRepository.buscarPorCategoriaYTitulo(categoria, q, pageable);
        } else if (tieneCategoria) {
            resultados = articuloRepository.buscarPorCategoria(categoria, pageable);
        } else if (tieneTexto) {
            resultados = articuloRepository.buscarPorTituloODescripcion(q, pageable);
        } else {
            resultados = articuloRepository.findAll(pageable);
        }

        return resultados.map(a -> new ArticuloResumen(
                a.getId(),
                a.getTitulo(),
                a.getCategoria(),
                a.getImagenUrl(),
                a.getFechaPublicacion()));
    }

    @Override
    public ArticuloResponse detalle(String id) {
        ArticuloCultural a = articuloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artículo no encontrado"));

        return new ArticuloResponse(
                a.getId(),
                a.getTitulo(),
                a.getDescripcion(),
                a.getCategoria(),
                a.getImagenUrl(),
                a.getFechaPublicacion());
    }

    @Override
    public ArticuloResponse crear(ArticuloRequest req) {
        ArticuloCultural articulo = new ArticuloCultural(
                req.getTitulo(),
                req.getDescripcion(),
                req.getCategoria()
        );
        articulo.setImagenUrl(req.getImagenUrl());

        ArticuloCultural guardado = articuloRepository.save(articulo);

        return new ArticuloResponse(
                guardado.getId(),
                guardado.getTitulo(),
                guardado.getDescripcion(),
                guardado.getCategoria(),
                guardado.getImagenUrl(),
                guardado.getFechaPublicacion());
    }

    @Override
    public ArticuloResponse editar(String id, ArticuloRequest req) {
        ArticuloCultural articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artículo no encontrado"));

        articulo.setTitulo(req.getTitulo());
        articulo.setDescripcion(req.getDescripcion());
        articulo.setCategoria(req.getCategoria());
        articulo.setImagenUrl(req.getImagenUrl());

        ArticuloCultural actualizado = articuloRepository.save(articulo);

        return new ArticuloResponse(
                actualizado.getId(),
                actualizado.getTitulo(),
                actualizado.getDescripcion(),
                actualizado.getCategoria(),
                actualizado.getImagenUrl(),
                actualizado.getFechaPublicacion());
    }

    @Override
    public void eliminar(String id) {
        if (!articuloRepository.existsById(id)) {
            throw new ResourceNotFoundException("Artículo no encontrado");
        }
        articuloRepository.deleteById(id);
    }
}

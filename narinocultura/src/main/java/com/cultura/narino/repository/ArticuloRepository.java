package com.cultura.narino.repository;

import com.cultura.narino.model.ArticuloCultural;
import com.cultura.narino.model.CategoriaEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends MongoRepository<ArticuloCultural, String> {

    @Query("{ 'categoria': ?0 }")
    Page<ArticuloCultural> buscarPorCategoria(CategoriaEnum categoria, Pageable pageable);

    @Query("{ $or: [ { 'titulo': { $regex: ?0, $options: 'i' } }, { 'descripcion': { $regex: ?0, $options: 'i' } } ] }")
    Page<ArticuloCultural> buscarPorTituloODescripcion(String texto, Pageable pageable);

    @Query("{ 'categoria': ?0, 'titulo': { $regex: ?1, $options: 'i' } }")
    Page<ArticuloCultural> buscarPorCategoriaYTitulo(CategoriaEnum categoria, String titulo, Pageable pageable);
}

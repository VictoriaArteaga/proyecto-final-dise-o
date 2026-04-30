package com.cultura.narino.repository;

import com.cultura.narino.model.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentario, String> {

    @Query("{ 'articuloId': ?0 }")
    List<Comentario> buscarPorArticulo(String articuloId);

    @Query(value = "{ 'autorId': ?0 }", delete = true)
    void eliminarPorAutor(String autorId);
}

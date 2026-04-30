package com.cultura.narino.repository;

import com.cultura.narino.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    @Query("{ 'correo': ?0 }")
    Optional<Usuario> buscarPorCorreo(String correo);

    @Query(value = "{ 'correo': ?0 }", exists = true)
    boolean existePorCorreo(String correo);
}


package com.cultura.narino.repository;

import com.cultura.narino.model.EventoCultural;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;

@Repository
public interface EventoRepository extends MongoRepository<EventoCultural, String> {

    @Query("{ 'fechaEvento': { $gte: ?0 } }")
    Page<EventoCultural> buscarEventosFuturos(Date fechaActual, Pageable pageable);
}

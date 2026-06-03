package com.cultura.narino.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "comentarios")
public class Comentario {

    @Id
    private String id;
    private String contenido;
    private Date fechaCreacion;

    private String autorId;
    private String articuloId;

    public Comentario() {
    }

    public Comentario(String contenido, String autorId, String articuloId) {
        this.contenido = contenido;
        this.autorId = autorId;
        this.articuloId = articuloId;
        this.fechaCreacion = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAutorId() {
        return autorId;
    }

    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }

    public String getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(String articuloId) {
        this.articuloId = articuloId;
    }

    @Override
    public String toString() {
        return "Comentario{id='" + id + "', autorId='" + autorId +
                "', articuloId='" + articuloId + "', fechaCreacion=" + fechaCreacion + "}";
    }
}
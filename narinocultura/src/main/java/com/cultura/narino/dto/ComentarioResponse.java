package com.cultura.narino.dto;

import java.util.Date;

public class ComentarioResponse {

    private String id;
    private String contenido;
    private String nombreAutor;
    private String articuloId;
    private Date fecha;

    public ComentarioResponse() {
    }

    public ComentarioResponse(String id, String contenido, String nombreAutor, String articuloId, Date fecha) {
        this.id = id;
        this.contenido = contenido;
        this.nombreAutor = nombreAutor;
        this.articuloId = articuloId;
        this.fecha = fecha;
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

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(String articuloId) {
        this.articuloId = articuloId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

package com.cultura.narino.dto;

import jakarta.validation.constraints.NotBlank;

public class ComentarioRequest {

    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;

    @NotBlank(message = "El articuloId es obligatorio")
    private String articuloId;

    public ComentarioRequest() {
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(String articuloId) {
        this.articuloId = articuloId;
    }
}

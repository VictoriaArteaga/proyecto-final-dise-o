package com.cultura.narino.dto;

import com.cultura.narino.model.CategoriaEnum;

import java.util.Date;

public class ArticuloResumen {

    private String id;
    private String titulo;
    private CategoriaEnum categoria;
    private String imagenUrl;
    private Date fechaPublicacion;

    public ArticuloResumen() {
    }

    public ArticuloResumen(String id, String titulo, CategoriaEnum categoria,
                           String imagenUrl, Date fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}

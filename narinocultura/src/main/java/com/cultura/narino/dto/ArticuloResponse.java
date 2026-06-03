package com.cultura.narino.dto;

import com.cultura.narino.model.Categoria;

import java.util.Date;

public class ArticuloResponse {

    private String id;
    private String titulo;
    private String descripcion;
    private Categoria categoria;
    private String imagenUrl;
    private Date fechaPublicacion;

    public ArticuloResponse() {
    }

    public ArticuloResponse(String id, String titulo, String descripcion, Categoria categoria,
                            String imagenUrl, Date fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public Date getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(Date fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
}
package com.cultura.narino.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "articulos")
public class ArticuloCultural {

    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private CategoriaEnum categoria;
    private String imagenUrl;
    private Date fechaPublicacion;
    private String autorId;

    public ArticuloCultural() {
    }

    public ArticuloCultural(String titulo, String descripcion, CategoriaEnum categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fechaPublicacion = new Date();
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getAutorId() {
        return autorId;
    }

    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }

    @Override
    public String toString() {
        return "ArticuloCultural{id='" + id + "', titulo='" + titulo + "', categoria=" + categoria +
                ", fechaPublicacion=" + fechaPublicacion + "}";
    }
}

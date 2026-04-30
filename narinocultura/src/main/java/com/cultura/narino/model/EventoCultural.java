package com.cultura.narino.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "eventos")
public class EventoCultural {

    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private Date fechaEvento;
    private String lugar;
    private String imagenUrl;
    private String administradorId;

    public EventoCultural() {
    }

    public EventoCultural(String titulo, String descripcion, Date fechaEvento, String lugar) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaEvento = fechaEvento;
        this.lugar = lugar;
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

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(String administradorId) {
        this.administradorId = administradorId;
    }

    @Override
    public String toString() {
        return "EventoCultural{id='" + id + "', titulo='" + titulo + "', lugar='" + lugar +
                "', fechaEvento=" + fechaEvento + "}";
    }
}

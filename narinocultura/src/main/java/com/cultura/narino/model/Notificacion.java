package com.cultura.narino.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import java.util.Date;

@Document(collection = "notificaciones")
public class Notificacion {

    @Id
    private String id;
    private String titulo;
    private String mensaje;
    private boolean leida;
    private Date fechaEnvio;
    private String destinatarioId;
    private String eventoId;

    public Notificacion() {
    }

    public Notificacion(String titulo, String mensaje, String destinatarioId, String eventoId) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.destinatarioId = destinatarioId;
        this.eventoId = eventoId;
        this.leida = false;
        this.fechaEnvio = new Date();
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(String destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    @Override
    public String toString() {
        return "Notificacion{id='" + id + "', titulo='" + titulo +
                "', leida=" + leida + ", destinatarioId='" + destinatarioId +
                "', eventoId='" + eventoId + "'}";
    }
}

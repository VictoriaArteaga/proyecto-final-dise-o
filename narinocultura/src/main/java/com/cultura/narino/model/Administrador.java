package com.cultura.narino.model;

import java.util.Date;

public class Administrador extends Usuario {

    private String nivelAcceso;
    private Date fechaAsignacion;

    public Administrador() {
        super();
    }

    public Administrador(String nombre, String correo) {
        super(nombre, correo);
        this.fechaAsignacion = new Date();
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    @Override
    public String toString() {
        return "Administrador{id='" + id + "', nombre='" + nombre + "', correo='" + correo +
                "', nivelAcceso='" + nivelAcceso + "', fechaAsignacion=" + fechaAsignacion + "}";
    }
}
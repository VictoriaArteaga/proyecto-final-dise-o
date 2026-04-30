package com.cultura.narino.model;

import java.util.Date;

public class Visitante extends Usuario {

    private Date fechaRegistro;

    public Visitante() {
        super();
        this.rol = Rol.VISITANTE;
    }

    public Visitante(String nombre, String correo) {
        super(nombre, correo);
        this.rol = Rol.VISITANTE;
        this.fechaRegistro = new Date();
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Visitante{id='" + id + "', nombre='" + nombre + "', correo='" + correo +
                "', fechaRegistro=" + fechaRegistro + "}";
    }
}

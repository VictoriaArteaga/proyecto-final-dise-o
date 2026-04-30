package com.cultura.narino.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "_class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Administrador.class, name = "Administrador"),
        @JsonSubTypes.Type(value = Visitante.class, name = "Visitante")
})
public class Usuario {

    @Id
    protected String id;
    protected String nombre;

    @Indexed(unique = true)
    protected String correo;

    protected String contrasena;
    protected Rol rol;

    public Usuario() {
    }

    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{id='" + id + "', nombre='" + nombre + "', correo='" + correo + "', rol=" + rol + "}";
    }
}
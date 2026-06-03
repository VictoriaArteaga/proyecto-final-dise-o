package com.cultura.narino.model;

import java.util.ArrayList;
import java.util.List;

public class Visitante extends Usuario {

    private List<String> articulosFavoritos = new ArrayList<>();

    public Visitante() {
        super();
    }

    public Visitante(String nombre, String correo) {
        super(nombre, correo);
    }

    public List<String> getArticulosFavoritos() {
        return articulosFavoritos;
    }

    public void setArticulosFavoritos(List<String> articulosFavoritos) {
        this.articulosFavoritos = articulosFavoritos;
    }

    public void agregarFavorito(String articuloId) {
        if (!articulosFavoritos.contains(articuloId)) {
            articulosFavoritos.add(articuloId);
        }
    }

    public void quitarFavorito(String articuloId) {
        articulosFavoritos.remove(articuloId);
    }

    @Override
    public String toString() {
        return "Visitante{id='" + id + "', nombre='" + nombre +
                "', favoritos=" + articulosFavoritos.size() + "}";
    }
}
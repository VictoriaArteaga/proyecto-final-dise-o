package com.cultura.narino.dto;

import com.cultura.narino.model.Rol;
import jakarta.validation.constraints.NotNull;

public class CambiarRolRequest {

    @NotNull(message = "El rol es obligatorio")
    private Rol rol;

    public CambiarRolRequest() {
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}


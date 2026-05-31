package com.cultura.narino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CambiarRolRequest {

    @NotBlank(message = "El rol es obligatorio")
    @Pattern(regexp = "ADMINISTRADOR|VISITANTE", message = "El rol debe ser ADMINISTRADOR o VISITANTE")
    private String rol;

    public CambiarRolRequest() {
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}


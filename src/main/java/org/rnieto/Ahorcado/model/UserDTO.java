package org.rnieto.Ahorcado.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class UserDTO {
    @NotNull(message = "{usuario.NotNull}")
    @NotBlank(message = "{usuario.NotBlank}")
    private String usuario;
    @NotNull(message = "{clave.NotNull}")
    @NotBlank(message = "{clave.NotBlank}")
    private String clave;

    public UserDTO() {}

    public UserDTO(String nombre,
                           String clave){
        this.usuario = nombre;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}

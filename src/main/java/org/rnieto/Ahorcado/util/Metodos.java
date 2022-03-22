package org.rnieto.Ahorcado.util;



import org.rnieto.Ahorcado.model.UserDTO;

public class Metodos {
    public static UserDTO root;

    public static void creaUsuario() {
    	root = new UserDTO("root@gmail.com",
                "root");
    }

}

package co.edu.uniquindio.poo.sistemanotificaciones.Model;

public class ClientUser extends User{


    public ClientUser(String nombre, String email, String telefono, String id) {
        super(nombre, email, telefono, id);
    }

    @Override
    public void formatearMensaje() {

    }

}

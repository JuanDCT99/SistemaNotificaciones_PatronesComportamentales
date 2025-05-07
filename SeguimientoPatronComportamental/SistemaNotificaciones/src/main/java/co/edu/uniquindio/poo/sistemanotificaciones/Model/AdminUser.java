package co.edu.uniquindio.poo.sistemanotificaciones.Model;

public class AdminUser extends User {

    public AdminUser(String nombre, String email, String telefono, String id) {
        super(nombre, email, telefono, id);
    }

    @Override
    public String formatearMensaje(String mensaje) {
        return "Admin: " + mensaje;
    }

    @Override
    public void actualizar(String mensaje) {

    }
}

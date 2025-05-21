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
        String mensajeFormateado = formatearMensaje(mensaje);
        if (estrategia != null) {
            estrategia.enviarNotificacion("Notificación para Administrador", mensajeFormateado);
        } else {
            System.out.println("No se ha configurado una estrategia de notificación para el administrador.");
        }
    }
}

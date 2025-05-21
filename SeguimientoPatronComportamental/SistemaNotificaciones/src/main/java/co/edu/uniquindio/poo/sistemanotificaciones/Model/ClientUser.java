package co.edu.uniquindio.poo.sistemanotificaciones.Model;

public class ClientUser extends User{


    public ClientUser(String nombre, String email, String telefono, String id, NotificationStrategy estrategia) {
        super(nombre, email, telefono, id);
        this.estrategia = estrategia;
    }

    public ClientUser(String nombre, String email, String telefono, String id) {
        super(nombre, email, telefono, id);
    }

    @Override
    public String formatearMensaje(String mensaje) {
        return "Cliente: " + mensaje;
    }
    @Override
    public void actualizar(String mensaje) {
        String mensajeFormateado = formatearMensaje(mensaje);
        estrategia.enviarNotificacion("Notificación para Cliente", mensajeFormateado);
    }


}

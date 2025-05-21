package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import java.io.Serializable;

public abstract class User implements Observador, Serializable {
    private static final long serialVersionUID = 1L;

    protected NotificationStrategy estrategia;
    protected NotificationPreferences preferencias;

    private String nombre;
    private String email;
    private String telefono;
    private String id;

    public User(String nombre, String email, String telefono, String id) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.id = id;
        this.preferencias = new NotificationPreferences();
        this.estrategia = this.preferencias.getNotificationStrategy();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationStrategy getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(NotificationStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public NotificationPreferences getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(NotificationPreferences preferencias) {
        this.preferencias = preferencias;
        this.estrategia = preferencias.getNotificationStrategy();
    }

    public void actualizarEstrategiaDesdePreferencias() {
        if (this.preferencias != null) {
            this.estrategia = this.preferencias.getNotificationStrategy();
        }
    }

    public abstract String formatearMensaje(String mensaje);



    public void actualizar(String mensaje) {
        String mensajeFormateado = formatearMensaje(mensaje);
        estrategia.enviarNotificacion("Notificacion", mensajeFormateado);

    }
    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}








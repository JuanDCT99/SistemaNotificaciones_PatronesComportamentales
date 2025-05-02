package co.edu.uniquindio.poo.sistemanotificaciones.Model;

public abstract class User implements Observador{

    protected NotificationStrategy estrategia;

    private String nombre;
    private String email;
    private String telefono;
    private String id;

    public User(String nombre, String email, String telefono, String id) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.id = id;
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

    public abstract void formatearMensaje();



    public void Actualizar(String mensaje){
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








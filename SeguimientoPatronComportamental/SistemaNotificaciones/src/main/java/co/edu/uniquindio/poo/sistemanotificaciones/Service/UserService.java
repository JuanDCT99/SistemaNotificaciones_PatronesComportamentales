package co.edu.uniquindio.poo.sistemanotificaciones.Service;

import co.edu.uniquindio.poo.sistemanotificaciones.Model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio para la gestión de usuarios en el sistema.
 * Implementa el patrón Singleton para asegurar una única instancia.
 */
public class UserService {
    private static UserService instance;
    private Map<String, User> usuarios;
    private EventManager eventManager;
    private User usuarioActual;

    private UserService() {
        // Cargar usuarios desde el sistema de persistencia
        List<User> usuariosCargados = PersistenceService.cargarUsuarios();

        usuarios = new HashMap<>();
        eventManager = new EventManager();

        // Si hay usuarios cargados, añadirlos al mapa y al gestor de eventos
        if (usuariosCargados != null && !usuariosCargados.isEmpty()) {
            for (User usuario : usuariosCargados) {
                usuarios.put(usuario.getId(), usuario);
                eventManager.agregarObservador(usuario);
            }
        }
    }

    /**
     * Obtiene la instancia única del servicio.
     * @return Instancia del UserService
     */
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param nombre Nombre del usuario
     * @param email Email del usuario
     * @param telefono Teléfono del usuario
     * @param id Identificador único del usuario
     * @param esAdmin Indica si el usuario es administrador
     * @return El usuario creado
     */
    public User registrarUsuario(String nombre, String email, String telefono, String id, boolean esAdmin) {
        User usuario;

        if (esAdmin) {
            usuario = new AdminUser(nombre, email, telefono, id);
        } else {
            usuario = new ClientUser(nombre, email, telefono, id);
        }

        usuarios.put(id, usuario);
        eventManager.agregarObservador(usuario);

        // Guardar usuarios en el sistema de persistencia
        guardarUsuarios();

        return usuario;
    }

    /**
     * Obtiene un usuario por su ID.
     * @param id ID del usuario
     * @return El usuario encontrado o null si no existe
     */
    public User obtenerUsuario(String id) {
        return usuarios.get(id);
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     * @param email Correo electrónico del usuario
     * @return El usuario encontrado o null si no existe
     */
    public User obtenerUsuarioPorEmail(String email) {
        for (User usuario : usuarios.values()) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Cambia la estrategia de notificación de un usuario.
     * @param userId ID del usuario
     * @param estrategia Nueva estrategia de notificación
     */
    public void cambiarEstrategiaNotificacion(String userId, NotificationStrategy estrategia) {
        User usuario = usuarios.get(userId);
        if (usuario != null) {
            usuario.setEstrategia(estrategia);
        }
    }

    /**
     * Envía una notificación a todos los usuarios registrados.
     * @param mensaje Mensaje a enviar
     */
    public void enviarNotificacionATodos(String mensaje) {
        eventManager.notificar(mensaje);
    }

    /**
     * Obtiene todos los usuarios registrados.
     * @return Lista de usuarios
     */
    public List<User> obtenerTodosLosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    /**
     * Establece el usuario actual de la sesión.
     * @param usuario Usuario actual
     */
    public void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
    }

    /**
     * Obtiene el usuario actual de la sesión.
     * @return Usuario actual
     */
    public User getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Verifica si existe un usuario con el ID especificado.
     * @param id ID a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existeUsuario(String id) {
        return usuarios.containsKey(id);
    }

    /**
     * Verifica si existe un usuario con el email especificado.
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existeUsuarioConEmail(String email) {
        for (User usuario : usuarios.values()) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Guarda los usuarios en el sistema de persistencia.
     */
    private void guardarUsuarios() {
        PersistenceService.guardarUsuarios(new ArrayList<>(usuarios.values()));
    }
}

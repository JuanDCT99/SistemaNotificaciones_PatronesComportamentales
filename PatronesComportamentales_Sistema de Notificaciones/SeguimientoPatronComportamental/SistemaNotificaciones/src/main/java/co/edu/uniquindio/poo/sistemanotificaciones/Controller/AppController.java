package co.edu.uniquindio.poo.sistemanotificaciones.Controller;

import co.edu.uniquindio.poo.sistemanotificaciones.Model.*;
import co.edu.uniquindio.poo.sistemanotificaciones.Service.UserService;

/**
 * Controlador principal de la aplicación.
 * Implementa el patrón Singleton para asegurar una única instancia.
 */
public class AppController {
    private static AppController instance;
    private UserService userService;
    
    private AppController() {
        userService = UserService.getInstance();
        
        // Crear algunos usuarios de prueba
        if (!userService.existeUsuario("admin1")) {
            User admin = userService.registrarUsuario("Administrador", "admin@sistema.com", "3001234567", "admin1", true);
            admin.setEstrategia(new EmailNotification());
        }
        
        if (!userService.existeUsuario("cliente1")) {
            User cliente = userService.registrarUsuario("Cliente Ejemplo", "cliente@ejemplo.com", "3109876543", "cliente1", false);
            cliente.setEstrategia(new SMSNotification());
        }
    }
    
    /**
     * Obtiene la instancia única del controlador.
     * @return Instancia del AppController
     */
    public static AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }
    
    /**
     * Inicia sesión con un usuario existente.
     * @param email Email del usuario
     * @return El usuario si existe, null en caso contrario
     */
    public User iniciarSesion(String email) {
        User usuario = userService.obtenerUsuarioPorEmail(email);
        if (usuario != null) {
            userService.setUsuarioActual(usuario);
        }
        return usuario;
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
        User usuario = userService.registrarUsuario(nombre, email, telefono, id, esAdmin);
        userService.setUsuarioActual(usuario);
        return usuario;
    }
    
    /**
     * Configura la estrategia de notificación por email para el usuario actual.
     */
    public void seleccionarEstrategiaEmail() {
        User usuario = userService.getUsuarioActual();
        if (usuario != null) {
            usuario.setEstrategia(new EmailNotification());
        }
    }
    
    /**
     * Configura la estrategia de notificación por SMS para el usuario actual.
     */
    public void seleccionarEstrategiaSMS() {
        User usuario = userService.getUsuarioActual();
        if (usuario != null) {
            usuario.setEstrategia(new SMSNotification());
        }
    }
    
    /**
     * Configura la estrategia de notificación push para el usuario actual.
     */
    public void seleccionarEstrategiaPush() {
        User usuario = userService.getUsuarioActual();
        if (usuario != null) {
            usuario.setEstrategia(new PushNotification());
        }
    }
    
    /**
     * Envía una notificación a todos los usuarios registrados.
     * @param mensaje Mensaje a enviar
     */
    public void enviarNotificacionATodos(String mensaje) {
        userService.enviarNotificacionATodos(mensaje);
    }
    
    /**
     * Obtiene el usuario actual de la sesión.
     * @return Usuario actual
     */
    public User getUsuarioActual() {
        return userService.getUsuarioActual();
    }
    
    /**
     * Verifica si existe un usuario con el email especificado.
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existeUsuarioConEmail(String email) {
        return userService.existeUsuarioConEmail(email);
    }
}

package co.edu.uniquindio.poo.sistemanotificaciones.Service;

import co.edu.uniquindio.poo.sistemanotificaciones.Model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Servicio para la persistencia de datos en el sistema.
 */
public class PersistenceService {
    private static final String USERS_FILE = "users.dat";
    private static final String NOTIFICATIONS_FILE = "notifications.dat";
    
    /**
     * Guarda la lista de usuarios en un archivo.
     * @param usuarios Lista de usuarios a guardar
     */
    public static void guardarUsuarios(List<User> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(USERS_FILE))) {
            oos.writeObject(new ArrayList<>(usuarios));
            System.out.println("Usuarios guardados correctamente");
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
    
    /**
     * Carga la lista de usuarios desde un archivo.
     * @return Lista de usuarios cargados
     */
    @SuppressWarnings("unchecked")
    public static List<User> cargarUsuarios() {
        List<User> usuarios = new ArrayList<>();
        File file = new File(USERS_FILE);
        
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                usuarios = (List<User>) ois.readObject();
                System.out.println("Usuarios cargados correctamente");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar usuarios: " + e.getMessage());
            }
        }
        
        return usuarios;
    }
    
    /**
     * Guarda el historial de notificaciones en un archivo.
     * @param notificaciones Lista de registros de notificaciones
     */
    public static void guardarHistorialNotificaciones(List<NotificationRecord> notificaciones) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(NOTIFICATIONS_FILE))) {
            oos.writeObject(new ArrayList<>(notificaciones));
            System.out.println("Historial de notificaciones guardado correctamente");
        } catch (IOException e) {
            System.err.println("Error al guardar historial de notificaciones: " + e.getMessage());
        }
    }
    
    /**
     * Carga el historial de notificaciones desde un archivo.
     * @return Lista de registros de notificaciones
     */
    @SuppressWarnings("unchecked")
    public static List<NotificationRecord> cargarHistorialNotificaciones() {
        List<NotificationRecord> notificaciones = new ArrayList<>();
        File file = new File(NOTIFICATIONS_FILE);
        
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                notificaciones = (List<NotificationRecord>) ois.readObject();
                System.out.println("Historial de notificaciones cargado correctamente");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar historial de notificaciones: " + e.getMessage());
            }
        }
        
        return notificaciones;
    }
    
    /**
     * Clase para representar un registro de notificación.
     */
    public static class NotificationRecord implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String userId;
        private String subject;
        private String content;
        private String type; // "EMAIL", "SMS", "PUSH"
        private long timestamp;
        
        /**
         * Constructor para un registro de notificación.
         * @param userId ID del usuario
         * @param subject Asunto de la notificación
         * @param content Contenido de la notificación
         * @param type Tipo de notificación
         */
        public NotificationRecord(String userId, String subject, String content, String type) {
            this.userId = userId;
            this.subject = subject;
            this.content = content;
            this.type = type;
            this.timestamp = System.currentTimeMillis();
        }
        
        // Getters
        public String getUserId() { return userId; }
        public String getSubject() { return subject; }
        public String getContent() { return content; }
        public String getType() { return type; }
        public long getTimestamp() { return timestamp; }
        
        @Override
        public String toString() {
            return "NotificationRecord{" +
                    "userId='" + userId + '\'' +
                    ", subject='" + subject + '\'' +
                    ", content='" + content + '\'' +
                    ", type='" + type + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
}

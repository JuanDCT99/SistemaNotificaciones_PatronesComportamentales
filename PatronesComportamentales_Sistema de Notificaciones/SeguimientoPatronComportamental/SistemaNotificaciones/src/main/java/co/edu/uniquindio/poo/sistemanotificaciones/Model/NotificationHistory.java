package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import co.edu.uniquindio.poo.sistemanotificaciones.Service.PersistenceService;
import co.edu.uniquindio.poo.sistemanotificaciones.Service.PersistenceService.NotificationRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar el historial de notificaciones.
 * Implementa el patrón Singleton para asegurar una única instancia.
 */
public class NotificationHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static NotificationHistory instance;
    private List<NotificationRecord> records;
    
    private NotificationHistory() {
        records = PersistenceService.cargarHistorialNotificaciones();
        if (records == null) {
            records = new ArrayList<>();
        }
    }
    
    /**
     * Obtiene la instancia única del historial de notificaciones.
     * @return Instancia del NotificationHistory
     */
    public static NotificationHistory getInstance() {
        if (instance == null) {
            instance = new NotificationHistory();
        }
        return instance;
    }
    
    /**
     * Añade un registro al historial de notificaciones.
     * @param userId ID del usuario
     * @param subject Asunto de la notificación
     * @param content Contenido de la notificación
     * @param type Tipo de notificación
     */
    public void addRecord(String userId, String subject, String content, String type) {
        records.add(new NotificationRecord(userId, subject, content, type));
        saveRecords();
    }
    
    /**
     * Obtiene las notificaciones de un usuario específico.
     * @param userId ID del usuario
     * @return Lista de registros de notificaciones del usuario
     */
    public List<NotificationRecord> getUserNotifications(String userId) {
        List<NotificationRecord> userRecords = new ArrayList<>();
        for (NotificationRecord record : records) {
            if (record.getUserId().equals(userId)) {
                userRecords.add(record);
            }
        }
        return userRecords;
    }
    
    /**
     * Obtiene todos los registros de notificaciones.
     * @return Lista de todos los registros de notificaciones
     */
    public List<NotificationRecord> getAllRecords() {
        return new ArrayList<>(records);
    }
    
    /**
     * Guarda los registros de notificaciones en el sistema de persistencia.
     */
    private void saveRecords() {
        PersistenceService.guardarHistorialNotificaciones(records);
    }
}

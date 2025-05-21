package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import co.edu.uniquindio.poo.sistemanotificaciones.Service.PersistenceService;
import co.edu.uniquindio.poo.sistemanotificaciones.Service.PersistenceService.NotificationRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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

    public static NotificationHistory getInstance() {
        if (instance == null) {
            instance = new NotificationHistory();
        }
        return instance;
    }
    

    public void addRecord(String userId, String subject, String content, String type) {
        records.add(new NotificationRecord(userId, subject, content, type));
        saveRecords();
    }

    public List<NotificationRecord> getUserNotifications(String userId) {
        List<NotificationRecord> userRecords = new ArrayList<>();
        for (NotificationRecord record : records) {
            if (record.getUserId().equals(userId)) {
                userRecords.add(record);
            }
        }
        return userRecords;
    }

    public List<NotificationRecord> getAllRecords() {
        return new ArrayList<>(records);
    }
    

    private void saveRecords() {
        PersistenceService.guardarHistorialNotificaciones(records);
    }
}

package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import java.io.Serializable;


public class NotificationPreferences implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String preferredMethod;
    private boolean receiveAllNotifications;
    private boolean onlyUrgentNotifications;
    
    /**
     * Constructor por defecto.
     */
    public NotificationPreferences() {
        this.preferredMethod = "EMAIL";
        this.receiveAllNotifications = true;
        this.onlyUrgentNotifications = false;
    }
    

    public NotificationPreferences(String preferredMethod, boolean receiveAllNotifications, boolean onlyUrgentNotifications) {
        this.preferredMethod = preferredMethod;
        this.receiveAllNotifications = receiveAllNotifications;
        this.onlyUrgentNotifications = onlyUrgentNotifications;
    }

    public String getPreferredMethod() {
        return preferredMethod;
    }
    

    public void setPreferredMethod(String preferredMethod) {
        this.preferredMethod = preferredMethod;
    }

    public boolean isReceiveAllNotifications() {
        return receiveAllNotifications;
    }
    

    public void setReceiveAllNotifications(boolean receiveAllNotifications) {
        this.receiveAllNotifications = receiveAllNotifications;
    }
    

    public boolean isOnlyUrgentNotifications() {
        return onlyUrgentNotifications;
    }
    

    public void setOnlyUrgentNotifications(boolean onlyUrgentNotifications) {
        this.onlyUrgentNotifications = onlyUrgentNotifications;
    }
    

    public NotificationStrategy getNotificationStrategy() {
        switch (preferredMethod) {
            case "SMS":
                return new SMSNotification();
            case "PUSH":
                return new PushNotification();
            case "EMAIL":
            default:
                return new EmailNotification();
        }
    }
    
    @Override
    public String toString() {
        return "NotificationPreferences{" +
                "preferredMethod='" + preferredMethod + '\'' +
                ", receiveAllNotifications=" + receiveAllNotifications +
                ", onlyUrgentNotifications=" + onlyUrgentNotifications +
                '}';
    }
}

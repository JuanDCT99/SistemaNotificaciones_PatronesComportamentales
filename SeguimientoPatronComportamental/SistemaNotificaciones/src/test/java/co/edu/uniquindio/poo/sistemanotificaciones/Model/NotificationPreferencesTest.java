package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para las preferencias de notificación.
 */
public class NotificationPreferencesTest {
    
    @Test
    public void testDefaultConstructor() {
        NotificationPreferences preferences = new NotificationPreferences();
        
        assertEquals("EMAIL", preferences.getPreferredMethod());
        assertTrue(preferences.isReceiveAllNotifications());
        assertFalse(preferences.isOnlyUrgentNotifications());
    }
    
    @Test
    public void testParameterizedConstructor() {
        NotificationPreferences preferences = new NotificationPreferences("SMS", false, true);
        
        assertEquals("SMS", preferences.getPreferredMethod());
        assertFalse(preferences.isReceiveAllNotifications());
        assertTrue(preferences.isOnlyUrgentNotifications());
    }
    
    @Test
    public void testGetNotificationStrategy() {
        NotificationPreferences emailPreferences = new NotificationPreferences("EMAIL", true, false);
        NotificationPreferences smsPreferences = new NotificationPreferences("SMS", true, false);
        NotificationPreferences pushPreferences = new NotificationPreferences("PUSH", true, false);
        
        assertTrue(emailPreferences.getNotificationStrategy() instanceof EmailNotification);
        assertTrue(smsPreferences.getNotificationStrategy() instanceof SMSNotification);
        assertTrue(pushPreferences.getNotificationStrategy() instanceof PushNotification);
    }
    
    @Test
    public void testSetters() {
        NotificationPreferences preferences = new NotificationPreferences();
        
        preferences.setPreferredMethod("SMS");
        assertEquals("SMS", preferences.getPreferredMethod());
        
        preferences.setReceiveAllNotifications(false);
        assertFalse(preferences.isReceiveAllNotifications());
        
        preferences.setOnlyUrgentNotifications(true);
        assertTrue(preferences.isOnlyUrgentNotifications());
    }
}

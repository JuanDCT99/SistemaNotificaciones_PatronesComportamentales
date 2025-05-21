package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para las estrategias de notificación.
 */
public class NotificationStrategyTest {
    
    private NotificationStrategy emailStrategy;
    private NotificationStrategy smsStrategy;
    private NotificationStrategy pushStrategy;
    
    @BeforeEach
    public void setUp() {
        emailStrategy = new EmailNotification();
        smsStrategy = new SMSNotification();
        pushStrategy = new PushNotification();
    }
    
    @Test
    public void testEmailNotification() {
        // No hay forma directa de probar la salida de System.out.println
        // Pero podemos verificar que no lance excepciones
        assertDoesNotThrow(() -> {
            emailStrategy.enviarNotificacion("Asunto de prueba", "Contenido de prueba");
        });
    }
    
    @Test
    public void testSMSNotification() {
        assertDoesNotThrow(() -> {
            smsStrategy.enviarNotificacion("Asunto de prueba", "Contenido de prueba");
        });
    }
    
    @Test
    public void testPushNotification() {
        assertDoesNotThrow(() -> {
            pushStrategy.enviarNotificacion("Asunto de prueba", "Contenido de prueba");
        });
    }
}

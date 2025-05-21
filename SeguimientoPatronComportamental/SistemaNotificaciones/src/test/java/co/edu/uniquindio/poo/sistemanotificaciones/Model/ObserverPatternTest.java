package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el patrón Observer.
 */
public class ObserverPatternTest {
    
    private EventManager eventManager;
    private User clientUser;
    private User adminUser;
    
    @BeforeEach
    public void setUp() {
        eventManager = new EventManager();
        clientUser = new ClientUser("Cliente Prueba", "cliente@test.com", "1234567890", "client1");
        adminUser = new AdminUser("Admin Prueba", "admin@test.com", "0987654321", "admin1");
        
        // Configurar estrategias de notificación
        clientUser.setEstrategia(new EmailNotification());
        adminUser.setEstrategia(new EmailNotification());
    }
    
    @Test
    public void testAgregarObservador() {
        // Inicialmente no hay observadores
        assertEquals(0, eventManager.getObservers().size());
        
        // Agregar un observador
        eventManager.agregarObservador(clientUser);
        assertEquals(1, eventManager.getObservers().size());
        assertTrue(eventManager.getObservers().contains(clientUser));
        
        // Agregar otro observador
        eventManager.agregarObservador(adminUser);
        assertEquals(2, eventManager.getObservers().size());
        assertTrue(eventManager.getObservers().contains(adminUser));
    }
    
    @Test
    public void testEliminarObservador() {
        // Agregar observadores
        eventManager.agregarObservador(clientUser);
        eventManager.agregarObservador(adminUser);
        assertEquals(2, eventManager.getObservers().size());
        
        // Eliminar un observador
        eventManager.eliminarObservador(clientUser);
        assertEquals(1, eventManager.getObservers().size());
        assertFalse(eventManager.getObservers().contains(clientUser));
        assertTrue(eventManager.getObservers().contains(adminUser));
        
        // Eliminar el otro observador
        eventManager.eliminarObservador(adminUser);
        assertEquals(0, eventManager.getObservers().size());
    }
    
    @Test
    public void testNotificar() {
        // No hay forma directa de probar la notificación sin mockear
        // Pero podemos verificar que no lance excepciones
        eventManager.agregarObservador(clientUser);
        eventManager.agregarObservador(adminUser);
        
        assertDoesNotThrow(() -> {
            eventManager.notificar("Mensaje de prueba");
        });
    }
}

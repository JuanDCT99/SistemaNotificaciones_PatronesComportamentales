package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import co.edu.uniquindio.poo.sistemanotificaciones.Controller.AppController;

public class SMSNotification implements NotificationStrategy{

    @Override
    public void enviarNotificacion(String asunto, String contenido) {
        // Simulación de envío de SMS
        System.out.println("ENVIANDO SMS:");
        System.out.println("Mensaje: " + asunto + " - " + contenido);
        System.out.println("SMS enviado correctamente.\n");

        // En una implementación real, aquí se conectaría con un servicio de SMS
        // Ejemplo: Twilio, Nexmo, etc.

        // Registrar en el historial de notificaciones
        User usuarioActual = AppController.getInstance().getUsuarioActual();
        if (usuarioActual != null) {
            NotificationHistory.getInstance().addRecord(
                usuarioActual.getId(), asunto, contenido, "SMS");
        }
    }
}

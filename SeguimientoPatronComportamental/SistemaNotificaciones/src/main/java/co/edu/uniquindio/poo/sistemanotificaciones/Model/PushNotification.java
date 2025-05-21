package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import co.edu.uniquindio.poo.sistemanotificaciones.Controller.AppController;

public class PushNotification implements NotificationStrategy{

    @Override
    public void enviarNotificacion(String asunto, String contenido) {
        // Simulación de envío de notificación push
        System.out.println("ENVIANDO NOTIFICACIÓN PUSH:");
        System.out.println("Título: " + asunto);
        System.out.println("Cuerpo: " + contenido);
        System.out.println("Notificación push enviada correctamente.\n");


        User usuarioActual = AppController.getInstance().getUsuarioActual();
        if (usuarioActual != null) {
            NotificationHistory.getInstance().addRecord(
                usuarioActual.getId(), asunto, contenido, "PUSH");
        }
    }
}

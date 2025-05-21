package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import co.edu.uniquindio.poo.sistemanotificaciones.Controller.AppController;

public class EmailNotification implements NotificationStrategy {

    @Override
    public void enviarNotificacion(String asunto, String contenido) {
        // Simulación de envío de email
        System.out.println("ENVIANDO EMAIL:");
        System.out.println("Asunto: " + asunto);
        System.out.println("Contenido: " + contenido);
        System.out.println("Email enviado correctamente.\n");



        User usuarioActual = AppController.getInstance().getUsuarioActual();
        if (usuarioActual != null) {
            NotificationHistory.getInstance().addRecord(
                usuarioActual.getId(), asunto, contenido, "EMAIL");
        }
    }
}

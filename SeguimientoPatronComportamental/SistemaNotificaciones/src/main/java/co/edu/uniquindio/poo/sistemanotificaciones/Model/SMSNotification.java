package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import co.edu.uniquindio.poo.sistemanotificaciones.Controller.AppController;

public class SMSNotification implements NotificationStrategy{

    @Override
    public void enviarNotificacion(String asunto, String contenido) {
        System.out.println("ENVIANDO SMS:");
        System.out.println("Mensaje: " + asunto + " - " + contenido);
        System.out.println("SMS enviado correctamente.\n");


        User usuarioActual = AppController.getInstance().getUsuarioActual();
        if (usuarioActual != null) {
            NotificationHistory.getInstance().addRecord(
                usuarioActual.getId(), asunto, contenido, "SMS");
        }
    }
}

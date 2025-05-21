package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import java.io.Serializable;

public interface NotificationStrategy extends Serializable {

    void enviarNotificacion(String asunto, String contenido);

    }


package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import java.io.Serializable;

public interface Observador extends Serializable {

    public void actualizar(String mensaje);

}

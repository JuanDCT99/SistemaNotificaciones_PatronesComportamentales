package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<Observador> observers = new ArrayList<>();

    public void agregarObservador(Observador o) {
        observers.add(o);
    };

    public void eliminarObservador (Observador o) {
        observers.remove(o);
    }

    public void notificar(String mensaje) {
        for (Observador o : observers) {
            o.actualizar(mensaje);
        }
    }

}

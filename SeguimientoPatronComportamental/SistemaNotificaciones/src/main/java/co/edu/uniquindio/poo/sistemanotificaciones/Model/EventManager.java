package co.edu.uniquindio.poo.sistemanotificaciones.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa el patrón Observer para gestionar las notificaciones.
 */
public class EventManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Observador> observers = new ArrayList<>();

    /**
     * Agrega un observador a la lista.
     * @param o Observador a agregar
     */
    public void agregarObservador(Observador o) {
        observers.add(o);
    };

    /**
     * Elimina un observador de la lista.
     * @param o Observador a eliminar
     */
    public void eliminarObservador (Observador o) {
        observers.remove(o);
    }

    /**
     * Notifica a todos los observadores con un mensaje.
     * @param mensaje Mensaje a enviar
     */
    public void notificar(String mensaje) {
        for (Observador o : observers) {
            o.actualizar(mensaje);
        }
    }

    /**
     * Obtiene la lista de observadores.
     * @return Lista de observadores
     */
    public List<Observador> getObservers() {
        return new ArrayList<>(observers);
    }
}

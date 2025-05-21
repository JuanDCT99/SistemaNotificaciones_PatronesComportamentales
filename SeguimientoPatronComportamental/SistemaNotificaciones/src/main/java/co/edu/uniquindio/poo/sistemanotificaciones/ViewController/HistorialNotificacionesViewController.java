package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.Controller.AppController;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.NotificationHistory;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.User;
import co.edu.uniquindio.poo.sistemanotificaciones.Service.PersistenceService.NotificationRecord;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para la vista del historial de notificaciones.
 */
public class HistorialNotificacionesViewController {

    @FXML private TableView<NotificationRecord> tablaNotificaciones;
    @FXML private TableColumn<NotificationRecord, String> colFecha;
    @FXML private TableColumn<NotificationRecord, String> colTipo;
    @FXML private TableColumn<NotificationRecord, String> colAsunto;
    @FXML private TableColumn<NotificationRecord, String> colContenido;
    @FXML private ComboBox<String> comboTipoNotificacion;
    @FXML private Button btnFiltrar;
    @FXML private Button btnLimpiarFiltro;
    @FXML private Button btnVolver;

    private AppController appController;
    private ObservableList<NotificationRecord> notificaciones;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Inicializa el controlador.
     */
    @FXML
    private void initialize() {
        appController = AppController.getInstance();
        
        // Configurar las columnas de la tabla
        colFecha.setCellValueFactory(cellData -> 
            new SimpleStringProperty(dateFormat.format(new Date(cellData.getValue().getTimestamp()))));
        
        colTipo.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getType()));
        
        colAsunto.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getSubject()));
        
        colContenido.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getContent()));
        
        // Configurar el combo box de tipos de notificación
        comboTipoNotificacion.getItems().addAll("Todos", "EMAIL", "SMS", "PUSH");
        comboTipoNotificacion.setValue("Todos");
        
        // Configurar eventos de botones
        btnFiltrar.setOnAction(this::filtrarNotificaciones);
        btnLimpiarFiltro.setOnAction(event -> {
            comboTipoNotificacion.setValue("Todos");
            cargarNotificaciones();
        });
        btnVolver.setOnAction(this::volver);
        
        // Cargar notificaciones
        cargarNotificaciones();
    }
    
    /**
     * Carga las notificaciones del usuario actual.
     */
    private void cargarNotificaciones() {
        User usuarioActual = appController.getUsuarioActual();
        if (usuarioActual != null) {
            List<NotificationRecord> historial = NotificationHistory.getInstance()
                .getUserNotifications(usuarioActual.getId());
            
            notificaciones = FXCollections.observableArrayList(historial);
            tablaNotificaciones.setItems(notificaciones);
        }
    }
    
    /**
     * Filtra las notificaciones según el tipo seleccionado.
     * @param event Evento de acción
     */
    private void filtrarNotificaciones(ActionEvent event) {
        String tipoSeleccionado = comboTipoNotificacion.getValue();
        User usuarioActual = appController.getUsuarioActual();
        
        if (usuarioActual != null) {
            List<NotificationRecord> historial = NotificationHistory.getInstance()
                .getUserNotifications(usuarioActual.getId());
            
            if (!"Todos".equals(tipoSeleccionado)) {
                historial = historial.stream()
                    .filter(record -> record.getType().equals(tipoSeleccionado))
                    .collect(Collectors.toList());
            }
            
            notificaciones = FXCollections.observableArrayList(historial);
            tablaNotificaciones.setItems(notificaciones);
        }
    }
    
    /**
     * Vuelve a la pantalla principal.
     * @param event Evento de acción
     */
    private void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemanotificaciones/InformacionPrograma.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("Error al volver a la pantalla principal: " + e.getMessage());
        }
    }
    
    /**
     * Muestra un mensaje de error.
     * @param mensaje Mensaje a mostrar
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

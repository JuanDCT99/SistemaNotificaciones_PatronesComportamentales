package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.Controller.AppController;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.NotificationPreferences;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para la vista de configuración de notificaciones.
 */
public class ConfiguracionNotificacionesViewController {

    @FXML private RadioButton radioEmail;
    @FXML private RadioButton radioSMS;
    @FXML private RadioButton radioPush;
    @FXML private ToggleGroup grupoMetodo;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefono;
    @FXML private CheckBox checkRecibirTodas;
    @FXML private CheckBox checkSoloUrgentes;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;
    
    private AppController appController;
    private User usuarioActual;
    
    /**
     * Inicializa el controlador.
     */
    @FXML
    private void initialize() {
        appController = AppController.getInstance();
        usuarioActual = appController.getUsuarioActual();
        
        // Configurar eventos de botones
        btnGuardar.setOnAction(this::guardarConfiguracion);
        btnCancelar.setOnAction(this::cancelar);
        
        // Configurar eventos de checkboxes
        checkRecibirTodas.setOnAction(event -> {
            if (checkRecibirTodas.isSelected()) {
                checkSoloUrgentes.setSelected(false);
            }
        });
        
        checkSoloUrgentes.setOnAction(event -> {
            if (checkSoloUrgentes.isSelected()) {
                checkRecibirTodas.setSelected(false);
            }
        });
        
        // Cargar preferencias actuales
        cargarPreferenciasActuales();
    }
    
    /**
     * Carga las preferencias actuales del usuario.
     */
    private void cargarPreferenciasActuales() {
        if (usuarioActual != null) {
            NotificationPreferences preferencias = usuarioActual.getPreferencias();
            
            // Configurar método preferido
            switch (preferencias.getPreferredMethod()) {
                case "SMS":
                    radioSMS.setSelected(true);
                    break;
                case "PUSH":
                    radioPush.setSelected(true);
                    break;
                case "EMAIL":
                default:
                    radioEmail.setSelected(true);
                    break;
            }
            
            // Configurar información de contacto
            txtEmail.setText(usuarioActual.getEmail());
            txtTelefono.setText(usuarioActual.getTelefono());
            
            // Configurar preferencias de notificación
            checkRecibirTodas.setSelected(preferencias.isReceiveAllNotifications());
            checkSoloUrgentes.setSelected(preferencias.isOnlyUrgentNotifications());
        }
    }
    
    /**
     * Guarda la configuración de notificaciones.
     * @param event Evento de acción
     */
    private void guardarConfiguracion(ActionEvent event) {
        if (usuarioActual != null) {
            // Obtener método preferido
            String metodoPreferido = "EMAIL";
            if (radioSMS.isSelected()) {
                metodoPreferido = "SMS";
            } else if (radioPush.isSelected()) {
                metodoPreferido = "PUSH";
            }
            
            // Actualizar información de contacto
            usuarioActual.setEmail(txtEmail.getText());
            usuarioActual.setTelefono(txtTelefono.getText());
            
            // Crear nuevas preferencias
            NotificationPreferences preferencias = new NotificationPreferences(
                metodoPreferido,
                checkRecibirTodas.isSelected(),
                checkSoloUrgentes.isSelected()
            );
            
            // Actualizar preferencias del usuario
            usuarioActual.setPreferencias(preferencias);
            
            mostrarConfirmacion("Configuración guardada correctamente");
            volver(event);
        }
    }
    
    /**
     * Cancela la configuración y vuelve a la pantalla principal.
     * @param event Evento de acción
     */
    private void cancelar(ActionEvent event) {
        volver(event);
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
     * Muestra un mensaje de confirmación.
     * @param mensaje Mensaje a mostrar
     */
    private void mostrarConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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

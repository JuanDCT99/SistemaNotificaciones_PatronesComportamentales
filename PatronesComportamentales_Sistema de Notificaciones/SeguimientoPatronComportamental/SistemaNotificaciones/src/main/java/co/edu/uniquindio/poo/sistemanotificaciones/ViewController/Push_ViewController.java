package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.Model.NotificationStrategy;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.PushNotification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Push_ViewController {

    @FXML private Label txtCelular;
    @FXML private TextField txtFieldCelular;
    @FXML private TextField txtFieldPushNotification;
    @FXML private Button btnEnviar;
    @FXML private Label txtInfo_mensaje;
    @FXML private TextArea txtFieldInfo_Mensaje;
    @FXML private Button btnVolver;

    private NotificationStrategy pushStrategy;

    @FXML
    private void initialize() {
        pushStrategy = new PushNotification();

        // Configurar eventos de botones
        btnEnviar.setOnAction(this::enviarPush);
        btnVolver.setOnAction(this::volver);

        // Configurar texto de ayuda
        txtFieldPushNotification.setPromptText("Escriba su notificación aquí...");
        txtFieldInfo_Mensaje.setEditable(false);
        txtFieldInfo_Mensaje.setText("Las notificaciones push son mensajes cortos que se envían directamente al dispositivo móvil del usuario, incluso cuando la aplicación no está abierta.");
    }

    private void enviarPush(ActionEvent event) {
        String celular = txtFieldCelular.getText();
        String mensaje = txtFieldPushNotification.getText();

        if (validarCampos(celular, mensaje)) {
            String asunto = "Notificación Push";
            pushStrategy.enviarNotificacion(asunto, mensaje);
            mostrarConfirmacion("Notificación push enviada correctamente al dispositivo: " + celular);
            limpiarCampos();
        } else {
            mostrarError("Por favor complete todos los campos");
        }
    }

    private boolean validarCampos(String celular, String mensaje) {
        return !celular.isEmpty() && !mensaje.isEmpty();
    }

    private void limpiarCampos() {
        txtFieldPushNotification.clear();
    }

    private void mostrarConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notificación Push Enviada");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

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
            mostrarError("Error al volver a la pantalla anterior");
        }
    }
}

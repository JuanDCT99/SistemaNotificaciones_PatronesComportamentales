package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.Model.NotificationStrategy;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.SMSNotification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SMS_ViewController {

    @FXML private ImageView imageUsuario;
    @FXML private TextField txtFieldEnviadoPor;
    @FXML private TextField txtRecibidoPor;
    @FXML private TextField txtFieldSMSnotification;
    @FXML private Label txtSendFor;
    @FXML private Label txtReciverFor;

    private NotificationStrategy smsStrategy;

    @FXML
    private void initialize() {
        smsStrategy = new SMSNotification();

        // Configurar eventos
        txtFieldSMSnotification.setPromptText("Escriba su mensaje aquí...");
    }

    @FXML
    private void enviarSMS() {
        String remitente = txtFieldEnviadoPor.getText();
        String destinatario = txtRecibidoPor.getText();
        String mensaje = txtFieldSMSnotification.getText();

        if (validarCampos(remitente, destinatario, mensaje)) {
            String asunto = "Mensaje de " + remitente;
            smsStrategy.enviarNotificacion(asunto, mensaje);
            mostrarConfirmacion("SMS enviado correctamente a: " + destinatario);
            limpiarCampos();
        } else {
            mostrarError("Por favor complete todos los campos");
        }
    }

    private boolean validarCampos(String remitente, String destinatario, String mensaje) {
        return !remitente.isEmpty() && !destinatario.isEmpty() && !mensaje.isEmpty();
    }

    private void limpiarCampos() {
        txtFieldSMSnotification.clear();
    }

    private void mostrarConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SMS Enviado");
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

    @FXML
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

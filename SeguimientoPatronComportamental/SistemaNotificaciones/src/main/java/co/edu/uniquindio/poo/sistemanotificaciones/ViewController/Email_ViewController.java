package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.Model.EmailNotification;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.NotificationStrategy;
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
import javafx.stage.Stage;

import java.io.IOException;

public class Email_ViewController {

    @FXML private Label txtPara;
    @FXML private TextField txtFieldPara;
    @FXML private Label txtAsunto;
    @FXML private TextField txtFieldAsunto;
    @FXML private TextField txtFieldMensajeEmail;
    @FXML private Button btnEnviar;
    @FXML private Button btnVolver;

    private NotificationStrategy emailStrategy;

    @FXML
    private void initialize() {
        emailStrategy = new EmailNotification();

        // Configurar eventos de botones
        btnEnviar.setOnAction(this::enviarEmail);
        btnVolver.setOnAction(this::volver);
    }

    private void enviarEmail(ActionEvent event) {
        String destinatario = txtFieldPara.getText();
        String asunto = txtFieldAsunto.getText();
        String contenido = txtFieldMensajeEmail.getText();

        if (validarCampos(destinatario, asunto, contenido)) {
            emailStrategy.enviarNotificacion(asunto, contenido);
            mostrarConfirmacion("Email enviado correctamente a: " + destinatario);
            limpiarCampos();
        } else {
            mostrarError("Por favor complete todos los campos");
        }
    }

    private boolean validarCampos(String destinatario, String asunto, String contenido) {
        return !destinatario.isEmpty() && !asunto.isEmpty() && !contenido.isEmpty();
    }

    private void limpiarCampos() {
        txtFieldPara.clear();
        txtFieldAsunto.clear();
        txtFieldMensajeEmail.clear();
    }

    private void mostrarConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notificación Enviada");
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

package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;

import co.edu.uniquindio.poo.sistemanotificaciones.Controller.AppController;
import co.edu.uniquindio.poo.sistemanotificaciones.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class informacionProgramaViewController {

    @FXML
    private Button BtnSMS;

    @FXML
    private Button btnMoreInfo;

    @FXML
    private Button btnEmail;

    @FXML
    private Button btnPush;

    @FXML
    private Button btnInicioSesion_Registro;

    @FXML
    private ImageView imageSMS;

    @FXML
    private ImageView imageUser;

    @FXML
    private ImageView imageEmail;

    @FXML
    private ImageView imagePush;

    @FXML
    private Label txtSeleccionar;

    @FXML
    private Label lblUsuarioActual;

    @FXML
    private Button btnEnviarNotificacionATodos;

    @FXML
    private Button btnVerHistorial;

    @FXML
    private Button btnConfiguracion;

    private AppController appController;

    @FXML
    private void initialize() {
        appController = AppController.getInstance();

        // Configurar eventos de botones
        BtnSMS.setOnAction(this::abrirSMS);
        btnEmail.setOnAction(this::abrirEmail);
        btnPush.setOnAction(this::abrirPush);
        btnMoreInfo.setOnAction(this::mostrarInformacion);
        btnInicioSesion_Registro.setOnAction(this::iniciarRegistro);

        // Si existe el botón para enviar notificaciones a todos
        if (btnEnviarNotificacionATodos != null) {
            btnEnviarNotificacionATodos.setOnAction(this::enviarNotificacionATodos);
        }

        // Si existe el botón para ver el historial de notificaciones
        if (btnVerHistorial != null) {
            btnVerHistorial.setOnAction(this::verHistorialNotificaciones);
        }

        // Si existe el botón para configurar notificaciones
        if (btnConfiguracion != null) {
            btnConfiguracion.setOnAction(this::configurarNotificaciones);
        }

        // Mostrar información del usuario actual si existe
        actualizarInformacionUsuario();
    }

    private void actualizarInformacionUsuario() {
        if (lblUsuarioActual != null) {
            User usuarioActual = appController.getUsuarioActual();
            if (usuarioActual != null) {
                lblUsuarioActual.setText("Usuario: " + usuarioActual.getNombre());
            } else {
                lblUsuarioActual.setText("No hay usuario conectado");
            }
        }
    }

    private void cambiarVista(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("Error al cambiar de vista: " + e.getMessage());
        }
    }

    @FXML
    private void iniciarRegistro(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/EntradaUsuario.fxml");
    }

    @FXML
    private void abrirSMS(ActionEvent event) {
        if (verificarUsuarioConectado()) {
            appController.seleccionarEstrategiaSMS();
            cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/SMS.fxml");
        }
    }

    @FXML
    private void abrirEmail(ActionEvent event) {
        if (verificarUsuarioConectado()) {
            appController.seleccionarEstrategiaEmail();
            cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/Email.fxml");
        }
    }

    @FXML
    private void abrirPush(ActionEvent event) {
        if (verificarUsuarioConectado()) {
            appController.seleccionarEstrategiaPush();
            cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/Push.fxml");
        }
    }

    private boolean verificarUsuarioConectado() {
        if (appController.getUsuarioActual() == null) {
            mostrarError("Debe iniciar sesión o registrarse primero");
            return false;
        }
        return true;
    }

    private void enviarNotificacionATodos(ActionEvent event) {
        if (verificarUsuarioConectado()) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enviar Notificación");
            dialog.setHeaderText("Enviar notificación a todos los usuarios");
            dialog.setContentText("Mensaje:");

            Optional<String> resultado = dialog.showAndWait();
            resultado.ifPresent(mensaje -> {
                if (!mensaje.isEmpty()) {
                    appController.enviarNotificacionATodos(mensaje);
                    mostrarConfirmacion("Notificación enviada a todos los usuarios");
                } else {
                    mostrarError("El mensaje no puede estar vacío");
                }
            });
        }
    }

    private void mostrarInformacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información del Sistema");
        alert.setHeaderText("Sistema de Notificaciones");
        alert.setContentText("Este sistema implementa tres patrones de comportamiento:\n\n" +
                "1. Patrón Observer: Para notificar a los usuarios suscritos\n" +
                "2. Patrón Strategy: Para seleccionar diferentes métodos de notificación\n" +
                "3. Patrón Template Method: Para personalizar el formato de los mensajes según el tipo de usuario");
        alert.showAndWait();
    }

    private void mostrarConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Abre la vista del historial de notificaciones.
     * @param event Evento de acción
     */
    private void verHistorialNotificaciones(ActionEvent event) {
        if (verificarUsuarioConectado()) {
            cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/HistorialNotificaciones.fxml");
        }
    }

    /**
     * Abre la vista de configuración de notificaciones.
     * @param event Evento de acción
     */
    private void configurarNotificaciones(ActionEvent event) {
        if (verificarUsuarioConectado()) {
            cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/ConfiguracionNotificaciones.fxml");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

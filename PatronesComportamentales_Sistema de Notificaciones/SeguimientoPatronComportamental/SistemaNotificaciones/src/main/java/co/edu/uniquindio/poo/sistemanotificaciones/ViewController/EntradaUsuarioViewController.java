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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class EntradaUsuarioViewController {

    @FXML
    private ImageView imageINICIAR_SESION;

    @FXML
    private ImageView imageREGISTRARSE;

    @FXML
    private Label txtIniciarSesion;

    @FXML
    private Label txtRegistrarse;

    @FXML
    private TextField txtFieldCorreoInicioSesion;

    @FXML
    private TextField txtFieldContraseñaInicioSesion;

    @FXML
    private TextField txtFieldNombre;

    @FXML
    private TextField txtFieldCorreo;

    @FXML
    private TextField txtFieldContraseña;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Label txtNombre;

    @FXML
    private Label txtCorreoRegistro;

    @FXML
    private Label txtContraseñaRegistro;

    @FXML
    private Label txtCorreo;

    @FXML
    private Label txtContraseña;

    @FXML
    private Button btnIniciarSesionUsuario;

    @FXML
    private Button btnVolver;

    private AppController appController;

    @FXML
    private void initialize() {
        appController = AppController.getInstance();

        // Configurar eventos de botones
        btnRegistrarse.setOnAction(this::registrarUsuario);
        btnIniciarSesionUsuario.setOnAction(this::iniciarSesionUsuario);
        btnVolver.setOnAction(this::volver);
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        String nombre = txtFieldNombre.getText();
        String correo = txtFieldCorreo.getText();
        String contraseña = txtFieldContraseña.getText();
        String telefono = ""; // En una versión futura se podría añadir este campo

        if (validarCamposRegistro(nombre, correo, contraseña)) {
            if (appController.existeUsuarioConEmail(correo)) {
                mostrarError("Ya existe un usuario con ese correo electrónico");
                return;
            }

            // Generar un ID único para el usuario
            String id = UUID.randomUUID().toString();

            // Registrar el usuario como cliente (no administrador)
            User usuario = appController.registrarUsuario(nombre, correo, telefono, id, false);

            if (usuario != null) {
                mostrarConfirmacion("Usuario registrado correctamente");
                cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/InformacionPrograma.fxml");
            } else {
                mostrarError("Error al registrar el usuario");
            }
        } else {
            mostrarError("Por favor complete todos los campos");
        }
    }

    @FXML
    private void iniciarSesionUsuario(ActionEvent event) {
        String correo = txtFieldCorreoInicioSesion.getText();
        String contraseña = txtFieldContraseñaInicioSesion.getText();

        if (validarCamposInicioSesion(correo, contraseña)) {
            User usuario = appController.iniciarSesion(correo);

            if (usuario != null) {
                mostrarConfirmacion("Bienvenido " + usuario.getNombre());
                cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/InformacionPrograma.fxml");
            } else {
                mostrarError("Usuario no encontrado");
            }
        } else {
            mostrarError("Por favor complete todos los campos");
        }
    }

    @FXML
    private void volver(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/InformacionPrograma.fxml");
    }

    private boolean validarCamposRegistro(String nombre, String correo, String contraseña) {
        return !nombre.isEmpty() && !correo.isEmpty() && !contraseña.isEmpty();
    }

    private boolean validarCamposInicioSesion(String correo, String contraseña) {
        return !correo.isEmpty() && !contraseña.isEmpty();
    }

    private void mostrarConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
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
}

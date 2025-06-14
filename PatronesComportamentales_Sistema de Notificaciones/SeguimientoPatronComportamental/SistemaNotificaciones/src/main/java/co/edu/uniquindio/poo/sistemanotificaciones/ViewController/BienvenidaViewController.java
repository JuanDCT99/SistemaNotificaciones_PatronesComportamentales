package co.edu.uniquindio.poo.sistemanotificaciones.ViewController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BienvenidaViewController {


    @FXML
    private Button btnIngresar;

    public void cambiar_VistaMenu(ActionEvent actionEvent) {

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
        }
    }


    @FXML
    private void regresarMenu(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/sistemanotificaciones/InformacionPrograma.fxml");
    }
}

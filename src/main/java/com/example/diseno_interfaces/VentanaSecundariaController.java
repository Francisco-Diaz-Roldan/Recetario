package com.example.diseno_interfaces;

import com.example.diseno_interfaces.models.Receta;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaSecundariaController implements Initializable {

    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private Button btnVolver;
    @javafx.fxml.FXML
    private Spinner<Double> spinner;

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        HelloApplication.loadFXML("ventanaPrincipal.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Receta r = Session.getRecetaActual();
        info.setText(r.toString());

        spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,10,5,0.25));
    }
}
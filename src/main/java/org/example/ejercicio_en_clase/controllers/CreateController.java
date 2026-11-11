package org.example.ejercicio_en_clase.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.ejercicio_en_clase.SceneNavigator;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox<String> cbEstado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbEstado.getItems().addAll("Pendiente", "En curso", "Completado");
    }

    @FXML
    public void onGuardarButtonClick(ActionEvent actionEvent) {
        SceneNavigator.navigateTo("main-view.fxml", "Main");
    }

    @FXML
    public void onCancelarButtonClick(ActionEvent actionEvent) {
        SceneNavigator.navigateTo("main-view.fxml", "Main");
    }
}

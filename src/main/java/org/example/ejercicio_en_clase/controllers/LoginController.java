package org.example.ejercicio_en_clase.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.example.ejercicio_en_clase.SceneNavigator;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @javafx.fxml.FXML
    public void login(ActionEvent actionEvent) {
        SceneNavigator.navigateTo("main-view.fxml", "Main");
    }
}

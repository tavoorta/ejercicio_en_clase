package org.example.ejercicio_en_clase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AppG1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneNavigator.setStage(stage);
        SceneNavigator.navigateTo("login-view.fxml","Login");
        stage.show();
    }
}

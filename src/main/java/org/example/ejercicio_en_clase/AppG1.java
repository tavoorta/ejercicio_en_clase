package org.example.ejercicio_en_clase;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.ejercicio_en_clase.data.DataBaseInitializer;
import org.example.ejercicio_en_clase.data.DataSourceFactory;

import java.io.IOException;

public class AppG1 extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        DataBaseInitializer.initialize(DataSourceFactory.getDataSource());

        SceneNavigator.setStage(stage);
        SceneNavigator.navigateTo("login-view.fxml","Login");
        stage.show();
    }
}

package org.example.ejercicio_en_clase.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.ejercicio_en_clase.SceneNavigator;
import org.example.ejercicio_en_clase.data.DAO;
import org.example.ejercicio_en_clase.data.DataSourceFactory;
import org.example.ejercicio_en_clase.data.EquipoMysqlDAO;
import org.example.ejercicio_en_clase.models.Equipo;
import org.example.ejercicio_en_clase.models.Estado;

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
    private ComboBox<Estado> cbEstado;

    private final DAO<Equipo> conectarDAO = new EquipoMysqlDAO(DataSourceFactory.getDataSource());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbEstado.getItems().addAll(Estado.values());
    }

    @FXML
    public void onGuardarButtonClick(ActionEvent actionEvent) {

        if (txtNombre.getText().isBlank() || dpFecha.getValue() == null || cbEstado.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Error");
            alert.setContentText("Nombre, Fecha y Estado son obligatorios.");
            alert.showAndWait();
            return;
        }

        Equipo equipo = new Equipo();
        equipo.setNombre(txtNombre.getText());
        equipo.setDescripcion(txtDescripcion.getText());
        equipo.setFecha(dpFecha.getValue());
        equipo.setEstado(cbEstado.getValue());

        conectarDAO.save(equipo);

        SceneNavigator.navigateTo("main-view.fxml", "Main");
    }

    @FXML
    public void onCancelarButtonClick(ActionEvent actionEvent) {
        SceneNavigator.navigateTo("main-view.fxml", "Main");
    }
}

package org.example.ejercicio_en_clase.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.ejercicio_en_clase.SceneNavigator;
import org.example.ejercicio_en_clase.data.DAO;
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
    @FXML
    private Label lblTitulo;

    private DAO<Equipo> dao;
    private Equipo equipo;

    public CreateController(DAO<Equipo> equipoDAO, Equipo equipo) {
        this.dao = equipoDAO;
        this.equipo = equipo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbEstado.getItems().addAll(Estado.values());

        if (equipo != null) {
            lblTitulo.setText("Modificar registro");
            txtNombre.setText(equipo.getNombre());
            txtDescripcion.setText(equipo.getDescripcion());
            dpFecha.setValue(equipo.getFecha());
            cbEstado.setValue(equipo.getEstado());
        }
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

        boolean esNuevo = (equipo == null);
        if (esNuevo) {
            equipo = new Equipo();
        }

        equipo.setNombre(txtNombre.getText());
        equipo.setDescripcion(txtDescripcion.getText());
        equipo.setFecha(dpFecha.getValue());
        equipo.setEstado(cbEstado.getValue());

        if (esNuevo) {
            dao.save(equipo);
        } else {
            dao.update(equipo);
        }

        SceneNavigator.navigateToInjection("main-view.fxml", "Main");
    }

    @FXML
    public void onCancelarButtonClick(ActionEvent actionEvent) {
        SceneNavigator.navigateToInjection("main-view.fxml", "Main");
    }
}

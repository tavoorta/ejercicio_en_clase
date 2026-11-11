package org.example.ejercicio_en_clase.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.ejercicio_en_clase.SceneNavigator;
import org.example.ejercicio_en_clase.data.DAO;
import org.example.ejercicio_en_clase.data.DataSourceFactory;
import org.example.ejercicio_en_clase.data.EquipoMysqlDAO;
import org.example.ejercicio_en_clase.models.Equipo;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Equipo> tablaRegistros;
    @FXML
    private TableColumn<Equipo, String> colId;
    @FXML
    private TableColumn<Equipo, String> colNombre;
    @FXML
    private TableColumn<Equipo, String> colFecha;
    @FXML
    private TableColumn<Equipo, String> colEstado;

    private final DAO<Equipo> conectarDAO = new EquipoMysqlDAO(DataSourceFactory.getDataSource());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory( (row)->{
            Equipo equipment = row.getValue();
            Long id = equipment.getId();
            return new SimpleStringProperty(String.valueOf(id));
        });
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colFecha.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getFecha() != null ? c.getValue().getFecha().toString() : ""));
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado().toString()));

        tablaRegistros.getItems().setAll(conectarDAO.findAll());
    }

    @FXML
    public void onCerrarSesionButtonClick(ActionEvent actionEvent) {
        SceneNavigator.navigateTo("login-view.fxml", "Login");
    }

    @FXML
    public void onNuevoButtonClick(ActionEvent actionEvent) {
        SceneNavigator.navigateTo("create-view.fxml", "Nuevo registro");
    }
}

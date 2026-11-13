package org.example.ejercicio_en_clase.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.ejercicio_en_clase.SceneNavigator;
import org.example.ejercicio_en_clase.data.DAO;
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

    private DAO<Equipo> dao;

    public MainController(EquipoMysqlDAO equipoDAO) {
        this.dao = equipoDAO;
    }

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

        dao.findAll().forEach( (equipo)->{
            System.out.println(equipo);
            tablaRegistros.getItems().add(equipo);
        });

        tablaRegistros.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                SceneNavigator.navigateToInjection("create-view.fxml", "Modificar registro", newSelection);
            }
        });
    }

    @FXML
    public void onCerrarSesionButtonClick(ActionEvent actionEvent) {
        SceneNavigator.navigateTo("login-view.fxml", "Login");
    }

    @FXML
    public void onNuevoButtonClick(ActionEvent actionEvent) {
        SceneNavigator.navigateToInjection("create-view.fxml", "Nuevo registro");
    }
}

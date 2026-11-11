module org.example.ejercicio_en_clase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires static lombok;


    opens org.example.ejercicio_en_clase to javafx.fxml;
    exports org.example.ejercicio_en_clase;
    exports org.example.ejercicio_en_clase.controllers;
    opens org.example.ejercicio_en_clase.controllers to javafx.fxml;
}
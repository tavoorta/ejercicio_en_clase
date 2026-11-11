package org.example.ejercicio_en_clase.data;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseInitializer {

    public static void initialize(DataSource ds){
        executeScript(ds, "/init.sql");
    }

    private static void executeScript(DataSource ds, String resourcePath) {
        InputStream is = DataBaseInitializer.class.getResourceAsStream(resourcePath);
        if (is == null) {
            throw new RuntimeException("No se encontró el recurso " + resourcePath);
        }

        StringBuilder script = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("--")) {
                    continue;
                }
                script.append(line).append('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = ds.getConnection();
             Statement st = conn.createStatement()) {
            for (String statement : script.toString().split(";")) {
                if (!statement.isBlank()) {
                    st.execute(statement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.ejercicio_en_clase.data;


import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    private static final String url = "jdbc:mysql://localhost:3010/ad";

    private static String username = System.getenv("USER");

    private static String password = System.getenv("PASSWORD");

    private static DataSource dataSource = null;

    public static DataSource getDataSource() {
        if(dataSource == null) {
            if(username == null || password  == null) {
                throw new RuntimeException("Username and/or password are mandatory");
            }
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL(url);
            ds.setUser(username.trim() );
            ds.setPassword(password.trim() );
            dataSource=ds;
        }
        return dataSource;
    }
}
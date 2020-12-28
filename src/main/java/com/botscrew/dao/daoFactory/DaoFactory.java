package com.botscrew.dao.daoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/university?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Connection getConnection(){
        Connection connection = null;

        String password = "Ivan20001974";
        String user = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException exception){
            System.out.println("Mysql driver not found");
        }
        try {
            connection = DriverManager.getConnection(URL, user, password);
        }catch (SQLException exception){
            System.out.println("Couldn't create sql connection");
        }

        return connection;
    }


}

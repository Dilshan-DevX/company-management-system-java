package com.nex.cms.connection;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

public class MySQL {

    public static Connection connection;

    public static void createConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/c.m.system", "root", "Dilshan@2003");
        }
    }

    public static int executeIUD(String query, Object... params) throws Exception {
        createConnection();
        PreparedStatement pst = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            pst.setObject(i + 1, params[i]);
        }
        return pst.executeUpdate();
    }

    public static ResultSet executeSearch(String query, Object... params) throws Exception {
        createConnection();
        PreparedStatement pst = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            pst.setObject(i + 1, params[i]);
        }
        return pst.executeQuery();
    }

}

package com.dgumarov;


import java.sql.*;


/**
 * Created by Dinar on 05.12.2016.
 */
public class Test {

    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:TEST.s3db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement statement = connection.createStatement();

        statement.execute("CREATE TABLE if not exists 'products' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'price' INT, 'quantity' INT);");

    }

}

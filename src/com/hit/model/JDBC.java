
package com.hit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
    public static String driver = "com.mysql.jdbc.Driver";
    public static String protocol = "jdbc:mysql://localhost:8889/todo";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = null;
            //Instantiating the dirver class will indirectly register
            //this driver as an available driver for DriverManager
            Class.forName(driver);
            //Getting a connection by calling getConnection
            connection = DriverManager.getConnection(protocol, "todo", "bar1234");
            statement = connection.createStatement();

            rs = statement.executeQuery(
                    "SELECT id,fee FROM items ORDER BY id");
            while (rs.next()) {
                System.out.println("id=" + rs.getInt("id")
                        + " fee=" + rs.getDouble("fee"));
            }
            statement.execute("DROP TABLE items");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) try {
                statement.close();
            } catch (Exception e) {
            }
            if (connection != null) try {
                connection.close();
            } catch (Exception e) {
            }
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }
}

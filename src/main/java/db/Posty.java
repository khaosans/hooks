package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by souriyakhaosanga on 5/16/14.
 * Copyright © 2014 Urban Airship and Contributors
 */
public class Posty {
    private static Connection connection;
    private static PreparedStatement ps;

    public static void main(String[] args) throws SQLException {
        writeToDb(args[0]);
    }

    public static void connectToDb() {
        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://mdcchiro.com/mdcchiro_monte", "mdcchiro_cristo",
                    "ayiruos08Yahoo3a");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public static void writeToDb(String message) throws SQLException {
        connectToDb();

        ps = connection.prepareStatement("INSERT INTO TWEETER (MESSAGE) VALUES (?);");
        ps.setString(1, message);
        ps.executeUpdate();
        connection.close();

        System.out.println("added this "+ message);
    }
}



package BazaDeDate;

import Game.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BazaDeDate {

    public static Handler handler;

    public static String GetLevelPathFromDataBase(int level){
        Connection c = null;
        Statement stmt = null;
        String path = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Dizo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Levels WHERE Level=" + level + ";" );
            path = rs.getString("LevelPath");

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return path;
    }

    public static String GetLevelPathDiamondsFromDataBase(int level){
        Connection c = null;
        Statement stmt = null;
        String path = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Dizo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Levels WHERE Level=" + level + ";" );
            path = rs.getString("LevelPathDiamant");

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return path;
    }
}

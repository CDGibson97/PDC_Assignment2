package assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBManager {

    private static final String USER_NAME = "pdc"; //your DB username
    private static final String PASSWORD = "pdc"; //your DB password
    private static final String URL = "jdbc:derby:PlayerDB;create=true";  //url of the DB host
    
    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        System.out.println(dbManager.getConnection());
    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if(this.conn == null){
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Database connected...");

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ResultSet queryDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}

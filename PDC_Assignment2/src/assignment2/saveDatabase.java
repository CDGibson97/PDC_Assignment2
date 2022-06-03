package assignment2;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


/**
 *
 * @author Callum Gibson
 * StudentID: 15906010
 */
public class saveDatabase {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public GameGUI gui;

    public saveDatabase(GameGUI guiPass) {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
        gui = guiPass;
    }
    
    public saveDatabase(){
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }


    public void closeConnection() {
        this.dbManager.closeConnections();
    }
    //This runs automatically saves after returning from battle, and will recognize whether player already exists, overwriting data as an autosave
    public void savePlayer(Player player) {
        try {
            gui.b1.setText("Continue");
            gui.b1.setActionCommand("continue");
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM PLAYER WHERE CHARACTERNAME = '" + player.name + "'");
            if (!rs.next()) {
                statement.executeUpdate("INSERT INTO PLAYER VALUES ('" + player.name
                        + "', " + player.level + " , " + player.points + " , " + player.score + ")");
                gui.textArea.setText("New player saved");
            } else {
                statement.executeUpdate("UPDATE PLAYER SET LEVEL = " + player.level
                        + ", POINTS = " + player.points
                        + ", SCORE = " + player.score
                        + " WHERE CHARACTERNAME = '" + player.name + "'");
                gui.textArea.setText("Player save updated");
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("SQL Exception");
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    //This method exists for dropping tables in the database, not accessible by the players.
    public void dropTable(String name){
        try{
            statement = conn.createStatement();
            statement.executeUpdate("Drop Table PLAYER");
            System.out.println("Table dropped");
            statement.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}

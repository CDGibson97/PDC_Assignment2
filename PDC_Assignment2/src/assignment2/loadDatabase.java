/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JProgressBar;

/**
 *
 * @author Callum Gibson;
 * StudentID: 15906010
 */
public class loadDatabase {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public GameGUI gui;

    public loadDatabase(GameGUI guiPass) {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
        gui = guiPass;
    }
    //Automatic load system that occurs when entering an existing name in the character creation screen, pulling through all the stats of that player
    public void loadPlayer(String playerName) throws SQLException {

        statement = conn.createStatement();
        
        ResultSet rs = null;

        String name = null;
        int level = 0;
        int points = 0;
        int score = 0;
        gui.b1.setText("Continue");
        gui.b1.setActionCommand("continue");

        gui.b2.setText(".");
        gui.b2.setActionCommand("nothing");

        gui.b3.setText(".");
        gui.b3.setActionCommand("nothing");

        gui.b4.setText(".");
        gui.b4.setActionCommand("nothing");

        try {
            rs = statement.executeQuery("SELECT * FROM PLAYER WHERE CHARACTERNAME = '" + playerName + "'");

            while (rs.next()) {
                gui.textArea.setText("Player Found");
                name = rs.getString("CHARACTERNAME");
                level = rs.getInt("LEVEL");
                points = rs.getInt("POINTS");
                score = rs.getInt("SCORE");
            }
            gui.player = new Player(name, level, points, score);
            gui.campfire = new campFire(gui.player, gui);
            rs.close();

            gui.textArea.append("\n" + gui.player.name + " has been loaded!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (gui.player.level == 0) {
            gui.textArea.setText("Player created");
            gui.player = new Player(playerName);
            gui.campfire = new campFire(gui.player, gui);
        }
        gui.playerHealth = new JProgressBar(0, gui.player.maxHealth);
        gui.playerHealth.setPreferredSize(new Dimension(200, 30));
        gui.playerHealth.setForeground(Color.green);

    }

    public void checkTableExists(String name) {
        try {
            
            statement = conn.createStatement();
            DatabaseMetaData dmd = this.conn.getMetaData();
            
            ResultSet rs = dmd.getTables(null, null, "PLAYER", null);

            boolean playerCheck = false;
            boolean leaderboardCheck = false;

            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                System.out.println(table_name);
                if (table_name.equalsIgnoreCase(name)) {
                    if (table_name.equalsIgnoreCase("PLAYER")) {
                        playerCheck = true;
                    } else if (name.equalsIgnoreCase("LEADERBOARD")) {
                        leaderboardCheck = true;
                    }
                   
                }
            }
            rs.close();
            if (!playerCheck) {
                statement.addBatch("CREATE TABLE PLAYER (CHARACTERNAME VARCHAR(12), LEVEL INT, POINTS INT, SCORE INT)");
            }
//            if (!leaderboardCheck) {
//                statement.addBatch("CREATE TABLE LEADERBOARD (RANKING INT, CHARACTERNAME VARCHAR(12), SCORE INT)");
//            }
            if (playerCheck == false) {
                statement.executeBatch();
                System.out.println("Table added");
            }
            statement.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

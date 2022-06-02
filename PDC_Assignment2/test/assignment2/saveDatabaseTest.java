/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assignment2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Callum Gibson;
 */
public class saveDatabaseTest {
    
    public saveDatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveScreen method, of class saveDatabase.
     */
    @Test
    public void testSaveScreen() {
        System.out.println("saveScreen");
        GameGUI gui = new GameGUI();
        saveDatabase instance = new saveDatabase(gui);
        instance.saveScreen();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of closeConnection method, of class saveDatabase.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        GameGUI gui = new GameGUI();
        saveDatabase instance = new saveDatabase(gui);
        instance.closeConnection();

    }

    /**
     * Test of savePlayer method, of class saveDatabase.
     */
    @Test
    public void testSavePlayer() {
        System.out.println("savePlayer");
        Player player = new Player("Test");
        GameGUI gui = new GameGUI();
        saveDatabase instance = new saveDatabase(gui);
        instance.savePlayer(player);
      
    }
    
}

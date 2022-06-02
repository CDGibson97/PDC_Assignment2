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
public class loadDatabaseTest {
    
    public loadDatabaseTest() {
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
     * Test of loadPlayer method, of class loadDatabase.
     */
    @Test
    public void testLoadPlayer() throws Exception {
        System.out.println("loadPlayer");
        String playerName = "";
        GameGUI gui = new GameGUI();
        loadDatabase instance = new loadDatabase(gui);
        instance.loadPlayer(playerName);
        
    }

    /**
     * Test of checkTableExists method, of class loadDatabase.
     */
    @Test
    public void testCheckTableExists() {
        System.out.println("checkTableExists");
        String name = "";
        GameGUI gui = new GameGUI();
        loadDatabase instance = new loadDatabase(gui);
        instance.checkTableExists(name);
    }
    
}

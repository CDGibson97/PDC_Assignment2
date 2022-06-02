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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of heal method, of class Player.
     */
    @Test
    public void testHeal() throws Exception {
        System.out.println("heal");
        GameGUI gui = new GameGUI();
        Player instance = new Player("Test");
        saveDatabase db = new saveDatabase(gui);
        
  
        boolean expResult = true;
        boolean result = instance.heal(gui);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of attack method, of class Player.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
       
        GameGUI gui = new GameGUI();
        Player instance = new Player("Test");
        saveDatabase db = new saveDatabase(gui);
        
        int expResult = 15;
        int result = instance.attack();
        assertEquals(expResult, result, 15);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}

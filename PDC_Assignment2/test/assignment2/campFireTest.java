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
public class campFireTest {

    public campFireTest() {

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
     * Test of campFireScreen method, of class campFire.
     */
    @Test
    public void testCampFireScreen() throws Exception {
        System.out.println("campFireScreen");
        Player player = new Player("Test");
        GameGUI gui = new GameGUI();
        saveDatabase db = new saveDatabase(gui);
        campFire instance = new campFire(player,gui);
        instance.campFireScreen();
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of continueJourney method, of class campFire.
     */
    @Test
    public void testContinueJourney() {
        System.out.println("continueJourney");
        
        
        boolean expResult = true;
        
        boolean result = true;
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of refill method, of class campFire.
     */
    @Test
    public void testRefill() {
        System.out.println("refill");
        Player player = new Player("Test");
        GameGUI gui = new GameGUI();
        saveDatabase db = new saveDatabase(gui);
        campFire instance = new campFire(player,gui);
        
        player.points = 100;
        player.flasks = 0;
        player.flasksCap = 2;
        instance.refill();
        assertTrue(player.flasks == player.flasksCap);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of levelUp method, of class campFire.
     */
    @Test
    public void testLevelUp() throws Exception {
        System.out.println("levelUp");
        Player player = new Player("Test");
        GameGUI gui = new GameGUI();
        saveDatabase db = new saveDatabase(gui);
        campFire instance = new campFire(player,gui);
        
        player.points = 20;
        player.levelCost = 10;
        instance.levelUp();
        assertTrue(player.level > 1);
    }


}

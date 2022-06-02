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
public class BattleTest {
    
    public BattleTest() {
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
     * Test of battleScreen method, of class Battle.
     */
    @Test
    public void testBattleScreen() throws Exception {
        System.out.println("battleScreen");
        Player player = new Player("Test");
        GameGUI gui = new GameGUI();
        saveDatabase db = new saveDatabase(gui);
        campFire campfire = new campFire(player,gui);
        Battle instance = new Battle(gui);
        instance.battleScreen();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of playerAttack method, of class Battle.
     */
    @Test
    public void testPlayerAttack() throws Exception {
        System.out.println("playerAttack");
        Player player = new Player("Test");
        GameGUI gui = new GameGUI();
        saveDatabase db = new saveDatabase(gui);
        campFire campfire = new campFire(player,gui);
        Battle instance = new Battle(gui);
        instance.playerAttack();
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of enemyAttack method, of class Battle.
     */
    @Test
    public void testEnemyAttack() throws Exception {
        System.out.println("enemyAttack");
        GameGUI gui = new GameGUI();
        Battle instance = new Battle(gui);
        instance.enemyAttack();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of magicAttack method, of class Battle.
     */
    @Test
    public void testMagicAttack() throws Exception {
        System.out.println("magicAttack");
        GameGUI gui = new GameGUI();
        Battle instance = new Battle(gui);
        instance.magicAttack();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}

package assignment2;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JProgressBar;

/**
 *
 * @author Callum Gibson StudentID 15906010
 */
public class campFire {

    //The purpose of this class is to handle all the rest and upgrade choices of the player character
    //Options include: 
    //Spend points on level ups and playerHealth potion refills
    Player player;

    GameGUI gui;
    saveDatabase db;

    public campFire(Player playerCharacter, GameGUI gui) {
        this.player = playerCharacter;
        this.gui = gui;
    }

    //CampFire Screen construction
    public void campFireScreen() throws InterruptedException {
        gui.player.currentHealth = gui.player.maxHealth;

        gui.playerHealthBar.setBounds(100, 15, 200, 30);
        gui.playerHealthBar.setBackground(Color.black);
        gui.playerHealthBar.setVisible(true);
        gui.gameWindow.add(gui.playerHealthBar);

        gui.playerHealth.setValue(gui.player.currentHealth);
        gui.playerHealthBar.add(gui.playerHealth);

        gui.enemyHealthBar.setBounds(500, 15, 200, 30);
        gui.enemyHealthBar.setBackground(Color.black);
        gui.gameWindow.add(gui.enemyHealthBar);

        gui.b1.setText("Continue");
        gui.b1.setActionCommand("continue");
        gui.enemyHealthBar.setVisible(false);
        gui.textArea.setForeground(Color.white);

        player.magicCharges = player.mcTotal;
        player.flasks = player.flasksCap;

        gui.textArea.append("\nWhat do you choose to do?");

        gui.b1Panel.setBounds(150, 350, 200, 200);
        gui.b2Panel.setVisible(true);

        gui.b1.setText("Continue to battle");
        gui.b1.setActionCommand("battle");

        gui.b2.setText("Level up");
        gui.b2.setActionCommand("level");

        gui.b3.setText("Start Final Boss");
        gui.b3.setActionCommand("boss");

        gui.b4.setText("Stats Screen");
        gui.b4.setActionCommand("stats");
    }

    //Returns true, and will continue into battle screen
    public boolean continueJourney() {
        return true;
    }
//Refills playerHealth potions at campfire, if player has enough points 

    public void refill() {
        if (player.points >= player.potionCost) {
            gui.textArea.setText("Potions have been refilled!");
            player.flasks = player.flasksCap;
        } else {
            gui.textArea.setText("You do not have enough points to refill your potions!");
        }
    }

    //Base function to allow leveling up at campfires, increasing all stats by fixed integers
    public void levelUp() throws InterruptedException {
        if (player.points >= player.levelCost) {
            player.maxHealth += 10;
            player.currentHealth = player.maxHealth;
            player.attack += 2;
            player.magicAttack += 3;
            player.level++;
            gui.textArea.setText("You are now Level " + player.level);
            player.points = player.points - player.levelCost;
            player.levelCost += 10;
            gui.playerHealth.setValue(player.currentHealth);
            if (player.level % 5 == 0) {
                player.flasksCap++;
                gui.textArea.setText("Potion Capacity Increased!");
            }
            if (player.level % 10 == 0) {
                player.mcTotal++;
                gui.textArea.setText("Magic Casts Increased!");
                player.magicCharges = player.mcTotal;
            }
            campFireScreen();
        } else {
            gui.textArea.setLineWrap(true);
            gui.textArea.setText("You do not have enough points to level up!\n");
            campFireScreen();

        }
    }

    public void statsOutput() {
        String output = "Name: " + player.name;
        output += "     Level: " + player.level;
        output += "\nAttack: " + player.attack;
        output += "     Healing Flasks: " + player.flasksCap;
        output += "\nMagic Attack: " + player.magicAttack;
        output += "     Magic Casts: " + player.mcTotal;

        gui.textArea.setText(output);

    }
}

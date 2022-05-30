package assignment2;

/**
 *
 * @author Callum Gibson StudentID 15906010
 */
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JProgressBar;

//This class runs the entire battle system, using a variety of methods from both in this class and other classes to make up the actions from the player and enemy
public final class Battle extends Thread {

    Player player;
    Enemy enemy;
    GameGUI gui;
    

//Regular Constructor for battles throughout the game  
    public Battle(GameGUI gg) {
        enemy = getEnemy();
        gui = gg;
        player = gui.player;
        enemy.stats(player);
    }

//Constructor for the final boss, activated by pressing 4 at the campfire
    public Battle(GameGUI gg, boolean finalBoss) {

        enemy = new Dragon();
        gui = gg;
        player = gui.player;
        enemy.stats(player);

    }

    public void playerAttack() throws InterruptedException, NullPointerException {
        
        gui.textArea.setText("");
        int playerAttack = player.attack();

        if (playerAttack > 0) {

            if (chanceHit() > 84) {

                gui.textArea.setText("You hit a Critical hit of " + (Math.round(player.attack * 1.5)));
                enemy.currentHealth -= (Math.round(player.attack * 1.5));
                gui.enemyHealth.setValue(enemy.currentHealth);
            } else {

                gui.textArea.setText("You hit for " + playerAttack);
                enemy.currentHealth -= playerAttack;
                gui.enemyHealth.setValue(enemy.currentHealth);
            }
        } else {
            gui.textArea.setText("You missed!");
        }
        if (enemy.currentHealth <= 0) {
            gui.battleEndScreen();
        } else{
        enemyAttack();
        }
    }

    public void enemyAttack() throws InterruptedException, NullPointerException {
        int enemyAttack = enemy.attack();

        if (enemyAttack > 0) {

            gui.textArea.append("\nEnemy attacked for " + enemyAttack);
            player.currentHealth -= enemyAttack;
            gui.playerHealth.setValue(player.currentHealth);

        } else {

            gui.textArea.append("\nEnemy missed!");
        }
        gui.battleScreen();
        if (player.currentHealth <= 0) { //Player loses
            
            gui.battleEndScreen();
        }
    }

    public void magicAttack() throws InterruptedException {
        if (player.magicCharges > 0) {
            gui.textArea.setText("You cast a magic spell!");

            if (chanceHit() <= 40) {
                gui.textArea.setText("Your spell missed!");
                player.magicCharges--;
            } else {
                gui.textArea.setText("Your spell hits for " + player.magicAttack + " damage!");
                enemy.currentHealth -= gui.player.magicAttack;
                player.magicCharges--;
                gui.enemyHealth.setValue(enemy.currentHealth);
                if (enemy.currentHealth <= 0) {
                    gui.battleEndScreen();
                }
                else{
                    enemyAttack();
                }
            }
        } else if (player.magicCharges <= 0){
            gui.textArea.setText("You have no magic!");
            gui.textArea.append("\nPlease try a different action");
        } else{
            enemyAttack();
        }
        
    }

    //This method gets and returns an enemy, with a higher chance of it returning a flying eye or zombie
    public Enemy getEnemy() {
        Random rand = new Random();
        boolean enemyAssignment = rand.nextBoolean();

        if (enemyAssignment) {
            return new zombie();
        } else {
            return new flyingEye();
        }

    }

    public void flee() throws InterruptedException, NullPointerException {
        Random rand = new Random();
        Boolean flee = rand.nextBoolean();

        if (flee) {
            gui.battleEndScreen();
        } else {
            gui.battleScreen();
        }

    }

    //This method calculates the chance that magic hits, as well as doubling for crit chance
    private int chanceHit() {
        Random rand = new Random();
        int chanceHit = rand.nextInt(100);
        return chanceHit;
    }

}

package assignment2;

/**
 *
 * @author Callum Gibson StudentID 15906010
 */
import java.awt.Color;
import java.util.Random;

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
    public Battle(boolean finalBoss) {

        enemy = new Dragon();
    }

    public void playerAttack() throws InterruptedException, NullPointerException {
        
        gui.textArea.setText("");
        int playerAttack = player.attack();

        if (playerAttack > 0) {

            if (chanceHit() > 84) {

                gui.textArea.setText("You hit a Critical hit of " + (Math.round(player.attack * 1.5)));
                enemy.currentHealth -= (Math.round(player.attack * 1.5));
            } else {

                gui.textArea.setText("You hit for " + playerAttack);
                enemy.currentHealth -= playerAttack;
            }
        } else {
            gui.textArea.setText("You missed!");
        }
        if (enemy.currentHealth <= 0) {
            gui.textArea.setText("You defeated the " + enemy.name + " ! You recieve " + enemy.points + " points!");
            player.points += enemy.points;
            gui.campFireScreen();
        } else{
        enemyAttack();
        }
    }

    public void enemyAttack() throws InterruptedException, NullPointerException {
        int enemyAttack = enemy.attack();

        if (enemyAttack > 0) {

            gui.textArea.append("\nEnemy attacked for " + enemyAttack);
            player.currentHealth -= enemyAttack;

        } else {

            gui.textArea.setText("Enemy missed!");
        }
        gui.battleScreen();
        if (player.currentHealth <= 0) { //Player loses
            gui.textArea.setText("You died.....");
            gui.textArea.setText("Lose all your points and return to Camp Fire");
            player.points = 0;
            player.currentHealth = player.maxHealth;
            gui.campFireScreen();
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
                if (enemy.currentHealth <= 0) {
                    gui.textArea.setText("You defeated the " + enemy.name + " ! You recieve " + enemy.points + " points!");
                    player.points += enemy.points;
                    gui.campFireScreen();
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
            gui.textArea.setText("You successfully got away!");
            gui.campFireScreen();
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

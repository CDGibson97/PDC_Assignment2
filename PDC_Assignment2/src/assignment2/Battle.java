package assignment2;

/**
 *
 * @author Callum Gibson StudentID 15906010
 */
import java.util.Random;

//This class runs the entire battle system, using a variety of methods from both in this class and other classes to make up the actions from the player and enemy
public class Battle extends Thread {

    Player player;
    Enemy enemy;

//Regular Constructor for battles throughout the game  
    public Battle(Player playerCharacter) {
        player = playerCharacter;
        enemy = getEnemy();

    }

//Constructor for the final boss, activated by pressing 4 at the campfire
    public Battle(Player playerCharacter, boolean finalBoss) {
        player = playerCharacter;
        enemy = new Dragon();
    }

//Main Screen for the battle, will run the entire battle instance
    public void Screen() throws InterruptedException {
        boolean end = false;
        userScanner input = new userScanner();
        enemy.stats(player);

        System.out.println("You stumble across a level " + enemy.level + " " + enemy.name);
        System.out.println("Get ready to fight!");

        while (!end) {

            System.out.println("");
            Thread.sleep(1000);

            System.out.println("Current Health: " + player.currentHealth);
            System.out.println("Enemy Health: " + enemy.currentHealth);
            System.out.println("1. Attack");
            System.out.println("2. Magic Attack [" + player.magicCharges + " left]");
            System.out.println("3. Heal [" + player.flasks + " remaining]");
            System.out.println("4. Flee");

            String userInput = input.getInput();

            //Quits the game
            if (userInput.equalsIgnoreCase("X")) {

                System.exit(0);

            //Selects the use of attack, which will deal a random amount of damage up to the players current attack, with a 15% chance of it being a crit.
            } else if (Integer.parseInt(userInput) == 1) {

                int playerAttack = player.attack();

                if (playerAttack > 0) {

                    if (chanceHit() > 84) {

                        System.out.println("You hit a Critical hit of " + (Math.round(player.attack * 1.5)));
                        enemy.currentHealth -= (Math.round(player.attack * 1.5));
                    } else {

                        System.out.println("You hit for " + playerAttack);
                        enemy.currentHealth -= playerAttack;
                    }
                } else {

                    System.out.println("You missed!");
                }

                //Selects the use of Magic spell, which is a higher flat damage than normal attack, but has a higher chance of missing.
            } else if (Integer.parseInt(userInput) == 2) {

                if (player.magicCharges > 0) {
                    System.out.println("You cast a magic spell!");
                    Thread.sleep(1000);

                    if (chanceHit() <= 40) {
                        System.out.println("Your spell missed!");
                        player.magicCharges--;
                    } else {
                        System.out.println("Your spell hits for " + player.magicAttack + " damage!");
                        enemy.currentHealth -= player.magicAttack;
                        player.magicCharges--;
                    }
                } else {
                    System.out.println("You have no magic!");
                }

                //Selects the option to heal, calling upon the heal method found in the Player class
            } else if (Integer.parseInt(userInput) == 3) {

                player.heal();

                //Selects the option to attempt to flee, which is a 50/50 chance (boolean).
            } else if (Integer.parseInt(userInput) == 4) {

                System.out.println("Attempting to Flee");
                Thread.sleep(1000);
                if (player.flee()) {
                    System.out.println("");
                    System.out.println("Successfully got away!");
                    Thread.sleep(1000);
                    end = true;

                }
            }

            Thread.sleep(1000);

            if (enemy.currentHealth > 0 && end != true) { //Determines if enemy has been killed, or if flee has been successful

                int enemyAttack = enemy.attack();

                if (enemyAttack > 0) {

                    System.out.println("Enemy attacked for " + enemyAttack);
                    player.currentHealth -= enemyAttack;

                } else {

                    System.out.println("Enemy missed!");
                }
            }

            if (player.currentHealth <= 0) { //Player loses

                System.out.println("You died!");
                System.out.println("Lose all your points and return to Camp Fire");
                player.points = 0;
                player.currentHealth = player.maxHealth;
                end = true;

            } else if (enemy.currentHealth <= 0) { //Player wins

                System.out.println("You won!");
                System.out.println("You got " + enemy.points + " points!");
                player.points += enemy.points;
                end = true;
            }
        }
    }

    //This method gets and returns an enemy, with a higher chance of it returning a flying eye or zombie
    public static Enemy getEnemy() {
        Random rand = new Random();
        boolean enemyAssignment = rand.nextBoolean();

        if (enemyAssignment) {
            return new zombie();
        } else {
            return new flyingEye();
        }

    }

    //This method calculates the chance that magic hits, as well as doubling for crit chance
    private int chanceHit() {
        Random rand = new Random();
        int chanceHit = rand.nextInt(100);
        return chanceHit;
    }

}

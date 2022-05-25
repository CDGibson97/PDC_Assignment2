package assignment1;

/**
 *
 * @author Callum Gibson 
 * StudentID 15906010
 */
public abstract class Enemy {

    String name;
    int maxHealth; //Max health
    int currentHealth;

    int attack; //Maximum attack possibility

    int points; //Used to purchase stat upgrades or flask restores at campfires

    int level;//Character's level, used to calculate enemy scaling, as well as future costs of levelups.

    public Enemy() {
        level = 0;
        maxHealth = 0;
        attack = 0;
        points = 0;
    }
    
    //abstract function to set stats of enemy based on Players level
    public void stats(Player character) {
    }
    
    //abstract function to allow both players and enemies to attack
    public int attack() {
        return 0;
    }
    

}

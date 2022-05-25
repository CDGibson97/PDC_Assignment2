package assignment1;

import java.util.Random;

/**
 *
 * @author Callum Gibson 
 * StudentID 15906010
 */
public class zombie extends Enemy {

    public zombie() {
        this.name = "Zombie";
        this.level = 1;
        this.maxHealth = 75; //base health is 50
        this.currentHealth = this.maxHealth;
        this.attack = 20; //base attack is 10
        this.points = 20;
    }
    
    @Override
    public void stats(Player character) {
        Random rand = new Random();
        this.level = rand.nextInt(character.level) + 1;
        this.maxHealth += ((level - 1) * 10);
        this.currentHealth = this.maxHealth;
        this.attack += (level * 5);
        this.points += (level * 10);
    }

    @Override
    public int attack() {
        Random rand = new Random();
        return rand.nextInt(this.attack);
    }

}

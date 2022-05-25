package assignment1;

import java.util.Random;

/**
 *
 * @author Callum Gibson 
 * StudentID 15906010
 */
public class flyingEye extends Enemy {

    public flyingEye() {
        this.name = "Flying Eye";
        this.level = 1;
        this.maxHealth = 50; //base health is 50
        this.currentHealth = maxHealth;
        this.attack = 10; //base attack is 10
        this.points = 20;
    }
    
    @Override
    public void stats(Player character) {
        Random rand = new Random();
        this.level = (rand.nextInt(character.level) + 1);
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

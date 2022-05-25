package assignment1;

import java.util.Random;

/**
 *
 * @author Callum Gibson 
 * StudentID 15906010
 */
public class Dragon extends Enemy {

    
//Final Boss enemy
    public Dragon() {
        this.name = "Dragon";
        this.level = 1;
        this.maxHealth = 200; //base health is 50
        this.currentHealth = this.maxHealth;
        this.attack = 40; //base attack is 10
        this.points = 75;
    }
    
    @Override
    public void stats(Player character) {
        this.level = character.level + 2;
        this.maxHealth += ((level - 1) * 10);
        this.currentHealth = this.maxHealth;
        this.attack += (level * 5);
    }

    @Override
    public int attack() {
        Random rand = new Random();
        return rand.nextInt(this.attack);
    }

}

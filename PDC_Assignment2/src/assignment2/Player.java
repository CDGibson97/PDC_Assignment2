package assignment2;

/**
 *
 * @author Callum Gibson 
 * StudentID: 15906010
 */
import java.util.Random;

public class Player {

    String name;
    int maxHealth; //Max playerHealth
    int currentHealth;

    int attack; //Maximum attack possibility

    int magicAttack; //Magic attack possibility
    int magicCharges; //The amount of times that a spell can be cast, refills at camp
    int mcTotal; //The cap to magicCharges; 

    int flasks; //Current heals, can only be restored at campfires
    int flasksCap;//Total heals

    int points; //Used to purchase stat upgrades or flask restores at campfires
    int score;

    int level;//Character's level, used to calculate enemy scaling, as well as future costs of levelups.
    int levelCost;//Cost to level up Character at CampFire
    int potionCost = 50;//Cost to refill potions at CampFire

//Used to create new Player from scratch
    public Player(String name) {
        this.name = name;
        this.level = 1;

        this.maxHealth = 150;//Default 150
        this.currentHealth = maxHealth;

        this.attack = 30;//Default 30

        this.magicAttack = 55;
        this.mcTotal = 1;//Starts at 1, increases every 10 levels
        this.magicCharges = this.mcTotal;

        this.flasksCap = 2;//Default 2
        this.flasks = flasksCap;

        this.points = 0;
        this.levelCost = 10;
    }

//Used to rebuild Player if detected in files
    public Player(String name, int level, int points, int score) {
        this.name = name;
        this.level = level;

        this.maxHealth = 150 + (level * 10);
        this.currentHealth = this.maxHealth;

        this.attack = 30 + (level * 2);

        this.magicAttack = 55 + (level * 3);
        this.mcTotal = 1;
        if (this.level >= 10) {
            if (this.level % 10 == 0) {
                this.mcTotal = 1 + (this.level / 10);
            }
            this.mcTotal += (Math.round(this.level) / 10);
        }

        this.magicCharges = this.mcTotal;

        this.flasksCap = 2;

        if (this.level >= 5) {
            if (this.flasksCap % 5 == 0) {
                this.flasksCap += +(level / 5);
            }
            this.flasksCap += (Math.round(this.level) / 5);
        }

        this.flasks = this.flasksCap;

        this.points = points;
        this.levelCost = this.level * 10;
    }
    
    
    //Everything below move to battle
    
    public int attack() { //returns an attack value
        Random rand = new Random();
        return rand.nextInt(this.attack);
    }

//Returns a true or false to dictate whether player can run away from monster or not
    public boolean heal(GameGUI gui) throws InterruptedException {
        if (this.flasks > 0) {
            int healthRestore = this.maxHealth / 2;
            if ((this.currentHealth + healthRestore) < this.maxHealth) {
                this.currentHealth += healthRestore;
                gui.textArea.setText("Health restored to " + this.currentHealth);
                gui.player.flasks--;
                gui.playerHealth.setValue(this.currentHealth);
                return true;
            } else {
                gui.player.currentHealth = gui.player.maxHealth;
                gui.textArea.setText("Player healed to full health!");
                gui.player.flasks--;
                gui.playerHealth.setValue(this.currentHealth);
                return true;
            }
        } else {
            gui.textArea.setText("You have no Potions!");
            
        }
        gui.battle.battleScreen();
        return false;
    }

//Player healing function in battles, based on 50% heals, or back to full if above. Players can heal on full playerHealth, as seen in many other games

}

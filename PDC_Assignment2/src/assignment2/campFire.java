package assignment2;

/**
 *
 * @author Callum Gibson StudentID 15906010
 */
public class campFire {

    //The purpose of this class is to handle all the rest and upgrade choices of the player character
    //Options include: 
    //Spend points on level ups and health potion refills
    Player player;
    saveFile file;

    public campFire(Player character) {
        this.player = character;

    }

    //Creates final boss instance
    public void battleBoss() {
        Battle battle = new Battle(player, true);
    }

    //Returns true, and will continue into battle screen
    public boolean continueJourney() {
        return true;
    }

//Refills health potions at campfire, if player has enough points 
    public void refill() {
        if (player.points >= player.potionCost) {
            System.out.println("Potions have been refilled!");
            player.flasks = player.flasksCap;
        } else {
            System.out.println("You do not have enough points to refill your potions!");
        }
    }


    //Base function to allow leveling up at campfires, increasing all stats by fixed integers
    public void levelUp() {
        if (player.points >= player.levelCost) {
            player.maxHealth += 10;
            player.currentHealth = player.maxHealth;
            player.attack += 2;
            player.magicAttack += 3;
            player.level++;
            System.out.println("You are now Level " + player.level);
            player.points = player.points - player.levelCost;
            player.levelCost += 10;
            if (player.level % 5 == 0) {
                player.flasksCap++;
                System.out.println("Potion Capacity Increased!");
            }
            if (player.level % 10 == 0) {
                player.mcTotal++;
                System.out.println("Magic Casts Increased!");
                player.magicCharges = player.mcTotal;
            }
        } else {
            System.out.println("You do not have points to level up");
        }
    }
}

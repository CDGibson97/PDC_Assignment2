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
    GameGUI gui;

    public campFire(Player character, GameGUI gui) {
        this.player = character;
        this.gui = gui;

    }
    //Creates final boss instance
    public void battleBoss() {
        Battle battle = new Battle(true);
    }

    //Returns true, and will continue into battle screen
    public boolean continueJourney() {
        return true;
    }
//Refills health potions at campfire, if player has enough points 
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
            if (player.level % 5 == 0) {
                player.flasksCap++;
                gui.textArea.setText("Potion Capacity Increased!");
            }
            if (player.level % 10 == 0) {
                player.mcTotal++;
                gui.textArea.setText("Magic Casts Increased!");
                player.magicCharges = player.mcTotal;
            }
            gui.campFireScreen();
        } else {
            gui.textArea.setLineWrap(true);
            gui.textArea.setText("You do not have enough points to level up!\n");
            gui.campFireScreen();
            
        }
    }
    
        public void statsOutput(){
        String output = "Name: "+ player.name;
        output += "     Level: " + player.level;
        output += "\nAttack: " + player.attack;
        output += "     Healing Flasks: "+player.flasksCap;
        output += "\nMagic Attack: " + player.magicAttack;
        output += "     Magic Casts: " +player.mcTotal;
        
        gui.textArea.setText(output);        
       
    }
}

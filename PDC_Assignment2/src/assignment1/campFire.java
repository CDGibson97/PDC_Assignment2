package assignment1;

/**
 *
 * @author Callum Gibson 
 * StudentID 15906010
 */
public class campFire {

    //The purpose of this class is to handle all the rest and upgrade choices of the player character
    //Options include: 
    //Spend points on level ups and health potion refills
    Player player;
    saveFile file;

    campFire(Player character, saveFile file) {
        this.player = character;
        this.file = file;
    }

    public boolean campFire() throws InterruptedException {
        userScanner input = new userScanner();
        player.magicCharges = player.mcTotal;
        
        Thread.sleep(1000);
        System.out.println("You find a place to rest...");

        System.out.println("");

        boolean run = true;
        while (run) {
            System.out.println("Current points: " + player.points);
            System.out.println("1. Carry On"); //Continue the loop, and battle random enemies to get stronger
            System.out.println("2. Level Up [Cost: " + this.player.levelCost + "]");
            System.out.println("3. Purchase more healing potions [50 points to refill]");
            System.out.println("4. Go to Castle [This will start the final boss]");
            System.out.println("5. Save Game");
            System.out.println("X. Quit Program");
            System.out.println("");

            String userInput = input.getInput();
            
                //Exit the application
            if (userInput.equalsIgnoreCase("X")) {
                System.exit(0);

            } else if (Integer.parseInt(userInput) == 1) {
                run = false;
                
                //Starts the level up process of the character.
            } else if (Integer.parseInt(userInput) == 2) {
                player.levelUp();
                
                //Refills all health potions 
            } else if (Integer.parseInt(userInput) == 3) {
                player.refill();
               
                //Begins the final boss, if you win, game ends, if lose, bounce back to the campfire.
            } else if (Integer.parseInt(userInput) == 4) {
                System.out.println("You go towards the castle, where the Dragon resides");
                Battle battle = new Battle(player, true);
                battle.Screen();
                if (battle.enemy.currentHealth <= 0) {
                    System.out.println("You bested the Dragon, and now the castle is yours!");
                    System.out.println("");
                    Thread.sleep(1000);
                    System.out.println("Game created by Callum Gibson");
                    System.exit(0);
                }
                //Saves character details 
            } else if (Integer.parseInt(userInput) == 5) {
                System.out.print("Saving");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                file.saveGame(player);
                Thread.sleep(500);
                System.out.println("\nSaving Complete!\n");
                Thread.sleep(1000);
            } 
            
        }
        return true; //Return true will continue the game, return false will close out of the program (once implemented)
    }
}

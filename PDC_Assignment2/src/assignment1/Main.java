package assignment1;

/**
 *
 * @author Callum Gibson 
 * StudentID 15906010
 */
public class Main {

//This class is the basis of the whole program, will run every other class from here
    public static void main(String[] args) throws InterruptedException {
        userScanner input = new userScanner();
        Player player = null;
        saveFile file = new saveFile();
        String userName;
        System.out.print("You see a Castle of in the distance, and a Dragon guarding from above");

        Thread.sleep(1000);
        System.out.print(".");

        Thread.sleep(1000);
        System.out.print(".");

        Thread.sleep(1000);
        System.out.println(".");

        Thread.sleep(1000);
        System.out.println("\n=====PDC Assignment One=====");
        System.out.println("Press 'X' at any time to quit");

        System.out.println("");
        boolean check = false;
        do {
            System.out.println("1. Start Game");
            System.out.println("X. Exit Game ");

            String userChoice = input.getInput();

            if (userChoice.equalsIgnoreCase("X")) {
                System.exit(0);

            } else if (Integer.parseInt(userChoice) == 1) {

                System.out.println("");
                System.out.println("Please enter your Characters name: ");//Players only have a First Name
                userName = input.getName();
                player = file.checkPlayer(userName);//If player name found in text document, pull through and recreate the character with existing stats
                check = true;

            } else {

                System.out.println("Invalid choice");
                System.out.println("");

                check = false;

            }
        } while (!check);

        boolean run = true;

//Begins gameplay loop, starting at the Campfire rest screen, and running the battle system when leaving the cmapfire
        while (run) {
            campFire rest = new campFire(player, file);
            rest.campFire();
            System.out.println("");
            Battle battle = new Battle(player);
            battle.Screen();
        }
    }
}

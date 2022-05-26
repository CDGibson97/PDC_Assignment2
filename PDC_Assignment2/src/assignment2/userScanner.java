package assignment2;

/**
 *
 * @author Callum Gibson StudentID 15906010
 */
import java.util.Scanner;

public class userScanner {

    //Fixed class that allows input validation and checks anywhere in the numerous apps.
    Scanner scan;

    public userScanner() {
        scan = new Scanner(System.in);
    }

    public String getName() {
        return scan.next();
    }

    
//Gets valid input, used during battle and campfire
    public String getInput() {
        String input;
        do {
            scan = new Scanner(System.in);
            input = scan.nextLine();
        } while (!isValidInput(input));

        return input;
    }

//Validates inputs, allowing X to be used to quit the game at any stage through out the game.
    public boolean isValidInput(String answer) {
        try {
            Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            if (answer.equalsIgnoreCase("X")) {
                return true;
            }
            System.out.println("Invalid Input.");
            System.out.println("Please enter a valid Input");
            return false;
        }
        return true;
    }
    
    
}

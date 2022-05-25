package assignment1;

/**
 *
 * @author Callum Gibson StudentID 15906010
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;

public class saveFile {

    private final HashMap<String, Player> players;
    private final String fileName;

    public saveFile() {
        this.fileName = "./resources/players.txt";
        this.players = new HashMap();
        this.getPlayers(fileName);
    }

//Gets the list of Players from the text file
    public final void getPlayers(String fileName) {
        FileInputStream fin;

        try {
            fin = new FileInputStream(fileName);
            Scanner fileScan = new Scanner(fin);

            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                Player p = new Player(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                this.players.put(p.name, p);

            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

//Check if player is existing in file, and brings them in if existing 
    public Player checkPlayer(String playerName) {
        Player player;

        if (this.players.containsKey(playerName)) {
            player = this.players.get(playerName);

            System.out.println("Welcome back " + player.name + "! Your current Level is " + player.level);
        } else {
            player = new Player(playerName);
            this.players.put(playerName, player);
            System.out.println("Welcome to the game " + player.name + ". Have fun!");
        }
        return player;
    }

//Saves player details, including player name, level, and current points
    public void saveGame(Player player) {
        this.players.put(player.name, player);
        try {
            FileOutputStream fos = new FileOutputStream(this.fileName);
            PrintWriter pw = new PrintWriter(fos);
            for (Player p : this.players.values()) {
                pw.println(p.name + " " + p.level + " " + p.points);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

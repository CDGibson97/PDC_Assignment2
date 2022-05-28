package assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Callum Gibson;
 */
public final class GameGUI implements ActionListener {

    JFrame gameWindow;
    PrintStream ps;
    public JTextArea textArea;
    private JPanel titleScreen;

    private Font font;
    private final Font regularFont = new Font("Times New Roman", Font.PLAIN, 20);

    private final JButton b1 = new JButton();
    private final JButton b2 = new JButton();
    private final JButton b3 = new JButton();
    private final JButton b4 = new JButton();
    public JPanel b1Panel = new JPanel();
    public JPanel b2Panel = new JPanel();
    public Player player;
    public campFire campfire;
    public Battle battle;

    public GameGUI() {
        this.gameWindow();
        this.buttons();
    }

    public void gameWindow() {
        gameWindow = new JFrame();
        gameWindow.setSize(800, 600);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLayout(null);
        gameWindow.setVisible(true);
        gameWindow.getContentPane().setBackground(Color.black);
    }

    public void buttons() {
        b1.setBackground(Color.GRAY);
        b1.setForeground(Color.WHITE);
        b1.setPreferredSize(new Dimension(300, 85));
        b1.setText("Start Game");
        b1.setActionCommand("start");
        b1.setFont(regularFont);

        b2.setBackground(Color.GRAY);
        b2.setForeground(Color.WHITE);
        b2.setPreferredSize(new Dimension(300, 85));
        b2.setActionCommand("exit");
        b2.setText("Exit Game");
        b2.setFont(regularFont);

        b3.setBackground(Color.gray);
        b3.setForeground(Color.WHITE);
        b3.setPreferredSize(new Dimension(300, 85));
        b3.setFont(regularFont);

        b4.setBackground(Color.gray);
        b4.setForeground(Color.WHITE);
        b4.setPreferredSize(new Dimension(300, 85));
        b4.setFont(regularFont);

        b1Panel.setBounds(300, 350, 200, 200);
        b1Panel.setBackground(Color.GRAY);
        b1Panel.add(b1);
        b1Panel.add(b2);

        b2Panel.setBounds(450, 350, 200, 200);
        b2Panel.setBackground(Color.gray);
        b2Panel.add(b3);
        b2Panel.add(b4);
        gameWindow.add(b2Panel);
        b2Panel.setVisible(false);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

    }

    public void titleScreen() throws InterruptedException {
        titleScreen = new JPanel();
        titleScreen.setBounds(100, 100, 600, 150);
        titleScreen.setBackground(Color.black);

        textArea = new JTextArea(50, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        ps = new PrintStream(new OutputTextConversion(textArea));
        System.setOut(ps);
        System.setErr(ps);

        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);

        titleScreen.add(textArea);
        gameWindow.add(titleScreen);
        gameWindow.add(b1Panel, BorderLayout.SOUTH);
        gameWindow.add(b2Panel, BorderLayout.SOUTH);
        gameWindow.setBackground(Color.black);

        //Add image
        textArea.setText("");
        font = new Font("Arial", Font.BOLD, 30);
        textArea.setFont(font);
        textArea.setText("=====PDC Assignment Two====");

    }

    //Home screen where player will save and load from automatically based on name scanner
    public void characterCreationScreen() {
        JFrame popout = new JFrame();
        boolean validate = false;
        String result;
        do {
            result = JOptionPane.showInputDialog(popout, "Please enter Character name");
            try {
                if (result.equals("")) {
                    JOptionPane.showMessageDialog(null, "Null input not valid!");
                } else {
                    validate = true;
                }
            } catch (NullPointerException ex) {
                validate = true;
            }
        } while (!validate);
        if (result != null) {
            textArea.setText("Welcome " + result);
            this.player = new Player(result);
            b1.setText("Continue");
            b1.setActionCommand("continue");

            campfire = new campFire(player, this);
        }
    }

    //CampFire Screen construction
    public void campFireScreen() throws InterruptedException {
        
        player.magicCharges = player.mcTotal;
        player.flasks = player.flasksCap;

        textArea.append("\nWhat do you choose to do?");
        
        
        b1Panel.setBounds(150, 350, 200, 200);
        b2Panel.setVisible(true);

        b1.setText("Continue to battle");
        b1.setActionCommand("battle");

        b2.setText("Level up");
        b2.setActionCommand("level");

        b3.setText("Start Final Boss");
        //Need to implement

        b4.setText("Stats Screen");
        b4.setActionCommand("stats");
    }

    public void battleScreen() throws InterruptedException {
        textArea.append("\nSelect Action...");

        b1.setText("Attack");
        b1.setActionCommand("attack");

        b2.setText("Heal");
        b2.setActionCommand("heal");

        b3.setText("Magic Attack");
        b3.setActionCommand("magic");

        b4.setText("Flee");
        b4.setActionCommand("flee");
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String choice = event.getActionCommand();
        switch (choice) {
            case "start":
                characterCreationScreen();
                break;

            case "exit":
                System.exit(0);

            case "continue": 
                try {
                textArea.setText("You find a place to rest");
                campFireScreen();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "battle":
                battle = new Battle(this);
                textArea.setText("");
                textArea.setText("You stumble across a level " + battle.enemy.level + " " + battle.enemy.name);
                try {
                    battleScreen();
                } catch (InterruptedException ex) {
                    textArea.setText("");
                    textArea.setText("Can not reach screen");
                } catch (NullPointerException ex) {
                    textArea.setText("");
                    textArea.setText("Cannot reach battle Screen");
                }
                break;

            case "level": 
                try {
                campfire.levelUp();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "stats":
                campfire.statsOutput();
                break;

            case "attack":
                try {
                battle.playerAttack();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "heal":
            
                try {
                player.heal(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                textArea.setText("");
                textArea.setText("Failed to heal");
            }

            break;

            case "magic":
                try {
                battle.magicAttack();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "flee": {
                try {
                    battle.flee();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GameGUI game = new GameGUI();
        game.titleScreen();

    }
}
//Begins gameplay loop, starting at the Campfire rest screen, and running the battle system when leaving the cmapfire
//        while (run) {
//            campFire rest = new campFire(player);
//            rest.campFire();
//            System.out.println("");
//            Battle battle = new Battle(player);
//            battle.Screen();
//        }
//    }
//}

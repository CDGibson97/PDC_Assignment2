package assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Timer;

/**
 *
 * @author Callum Gibson;
 */
public final class GameGUI implements ActionListener {

    JFrame gameWindow;
    PrintStream ps;
    private JTextArea textArea;
    private JPanel titleScreen;
    public JPanel b1Panel = new JPanel();
    private Font font;
    private Font regularFont = new Font("Times New Roman", Font.PLAIN, 20);
    private JButton b1 = new JButton();
    private JButton b2;
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
        b1 = new JButton();
        b1.setBackground(Color.GRAY);
        b1.setForeground(Color.WHITE);
        b1.setText("Start Game");
        b1.setActionCommand("start");
        b1.setFont(regularFont);

        b2 = new JButton();
        b2.setBackground(Color.GRAY);
        b2.setForeground(Color.WHITE);
        b2.setActionCommand("exit");
        b2.setText("Exit Game");
        b2.setFont(regularFont);

        b1Panel.setBounds(300, 350, 100, 80);
        b1Panel.setBackground(Color.GRAY);

        b1Panel.add(b1);
        b1Panel.add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void titleScreen() throws InterruptedException {
        titleScreen = new JPanel();
        titleScreen.setBounds(100, 100, 600, 150);
        titleScreen.setBackground(Color.black);

        textArea = new JTextArea(50, 10);
        ps = new PrintStream(new OutputTextConversion(textArea));
        System.setOut(ps);
        System.setErr(ps);

        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);

        titleScreen.add(textArea);
        gameWindow.add(titleScreen);
        gameWindow.add(b1Panel, BorderLayout.SOUTH);
        gameWindow.setBackground(Color.black);

        //Add image
        textArea.setText("");
        font = new Font("Arial", Font.BOLD, 30);
        textArea.setFont(font);
        System.out.println("=====PDC Assignment Two====");

    }

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
            System.out.println("Welcome " + result);
            this.player = new Player(result);
            b1.setText("Continue");
            b1.setActionCommand("continue");

            campfire = new campFire(player);
        }
    }

    public void campFireScreen() throws InterruptedException {
        System.out.println("You find a place to rest");
        textArea.setText("");
        System.out.println();
        System.out.println("What do you choose to do?");

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
            case "continue": {
                try {
                    campFireScreen();
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

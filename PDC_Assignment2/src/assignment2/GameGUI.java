package assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.SQLException;

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

    public final JButton b1 = new JButton();
    public final JButton b2 = new JButton();
    public final JButton b3 = new JButton();
    public final JButton b4 = new JButton();
    public final JButton quit = new JButton();

    public JPanel b1Panel = new JPanel();
    public JPanel b2Panel = new JPanel();
    public JPanel quitPanel = new JPanel();
    public JPanel playerHealthBar = new JPanel();
    public JPanel enemyHealthBar = new JPanel();

    public JProgressBar playerHealth;
    public JProgressBar enemyHealth;

    public Player player;
    public Enemy enemy;
    public campFire campfire;
    public Battle battle;

    saveDatabase db;
    loadDatabase ldb;

    public GameGUI() {
        this.gameWindow();
        this.buttons();
        db = new saveDatabase(this);
        ldb = new loadDatabase(this);
        ldb.checkTableExists("Player");
        player = new Player("");
    }

    public void gameWindow() {
        gameWindow = new JFrame();
        gameWindow.setSize(800, 600);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLayout(null);
        gameWindow.setVisible(true);
        gameWindow.getContentPane().setBackground(Color.black);
        textArea = new JTextArea();
        playerHealth = new JProgressBar();
        enemyHealth = new JProgressBar();
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

        quit.setBackground(Color.gray);
        quit.setForeground(Color.white);
        quit.setPreferredSize(new Dimension(100, 50));
        quit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        quit.setText("Quit");
        quit.setActionCommand("finish");

        quitPanel.setBounds(350, 10, 100, 50);
        quitPanel.setBackground(Color.gray);
        quitPanel.add(quit);
        gameWindow.add(quitPanel);
        quitPanel.setVisible(true);

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
        quit.addActionListener(this);

    }

    public void titleScreen() throws InterruptedException {
        titleScreen = new JPanel();
        titleScreen.setBounds(100, 100, 600, 150);
        titleScreen.setBackground(Color.black);

        textArea = new JTextArea(50, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        System.setOut(ps);
        System.setErr(ps);

        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);

        titleScreen.add(textArea);
        gameWindow.add(titleScreen);
        gameWindow.add(b1Panel, BorderLayout.SOUTH);
        gameWindow.add(b2Panel, BorderLayout.SOUTH);
        gameWindow.setBackground(Color.black);

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
            try {
                ldb.loadPlayer(result);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public void battleEndScreen() throws InterruptedException {
        enemyHealth.setValue(0);

        b1.setText("Continue");
        b1.setActionCommand("save");

        b2.setText(".");
        b2.setActionCommand("nothing");

        b3.setText(".");
        b3.setActionCommand("nothing");

        b4.setText(".");
        b4.setActionCommand("nothing");

        if (player.currentHealth <= 0) {
            textArea.setForeground(Color.red);
            textArea.setText("You died.....");
            textArea.setText("Lose all your points and return to Camp Fire");
            player.points = 0;
        } else if (battle.enemy.name.equals("Dragon") && battle.enemy.currentHealth <= 0) {
            textArea.setText("You've beaten the dragon! You win!");
            textArea.append("\n\nGame created by Callum Gibson");
            b1.setText("Finish");
            b1.setActionCommand("finish");
        } else if (enemy.currentHealth <= 0) {
            textArea.setText("You defeated the " + enemy.name + " ! You recieve " + enemy.points + " points!");
            player.points += enemy.points;
        } else {
            textArea.setText("You successfully got away!");
        }
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
            case "save":
                if (enemyHealth != null) {
                    enemyHealthBar.remove(enemyHealth);
                }
                db.savePlayer(player);
                break;
            case "continue": 

                try {
                textArea.setText("You find a place to rest");
                campfire.campFireScreen();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "battle":

                battle = new Battle(this);
                enemy = battle.enemy;
                textArea.setText("");
                textArea.setText("You stumble across a level " + battle.enemy.level + " " + battle.enemy.name);

                enemyHealth = new JProgressBar(0, enemy.maxHealth);
                enemyHealth.setPreferredSize(new Dimension(200, 30));
                enemyHealth.setForeground(Color.green);

                enemyHealthBar.add(enemyHealth);
                enemyHealth.setForeground(Color.red);

                enemyHealthBar.setVisible(true);
                try {
                    battle.battleScreen();
                } catch (InterruptedException ex) {
                    textArea.setText("");
                    textArea.setText("Can not reach screen");
                } catch (NullPointerException ex) {
                    textArea.setText("");
                    textArea.setText("Cannot reach battle Screen");
                }
                break;

            case "boss":
                battle = new Battle(this, true);
                textArea.setText("");
                textArea.setText("You go to face the dragon! It is level " + battle.enemy.level + "! Be Ready!");

                enemyHealth = new JProgressBar(0, battle.enemy.maxHealth);
                enemyHealth.setPreferredSize(new Dimension(200, 30));
                enemyHealth.setForeground(Color.green);

                enemyHealthBar.add(enemyHealth);
                enemyHealth.setForeground(Color.red);

                enemyHealthBar.setVisible(true);

                try {
                    battle.battleScreen();
                } catch (InterruptedException ex) {
                    textArea.setText("Can not reach screen");
                } catch (NullPointerException ex) {
                    textArea.setText("Screen method is null");
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
                b2.setText("Heal (" + player.flasks + " Left)");
                player.heal(this);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            } catch (NullPointerException ex) {
                textArea.setText("");
                textArea.setText("Failed to heal");
            }
            break;

            case "magic":
                try {
                b3.setText("Magic Attack (" + player.magicCharges + " Left)");
                battle.magicAttack();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            break;

            case "flee": 
                try {
                battle.flee();
                
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            break;
            //Proceeds to save screen
            case "yes":
                db.savePlayer(player);
                textArea.setText("Save Successful");
                break;
            //Skips saving, and returns to campfire
            case "no":
                try {
                campfire.campFireScreen();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            break;
            //Sets button to quit game
            case "finish":
                System.exit(0);
                break;
            //Sets buttons to no action, allowing for pauses inbetween screens
            case "nothing":
                break;
        }
    }

}

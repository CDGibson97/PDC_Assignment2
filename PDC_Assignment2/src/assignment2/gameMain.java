/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 *
 * @author Callum Gibson;
 * StudentID: 15906010
 */
public class gameMain {
    public static void main(String[] args) {
        GameGUI gui = new GameGUI();
        try{
            gui.titleScreen();
        }catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}

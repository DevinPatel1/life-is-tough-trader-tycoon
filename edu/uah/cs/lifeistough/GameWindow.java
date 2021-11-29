/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

import javax.swing.JFrame;

/**
 * This class extends JFrame and will serve as the window
 * that the user will interact with. All panels will be pushed
 * to this window as the player interacts with the GUI.
 * 
 * @author Luke Farris
 */
public class GameWindow extends JFrame {

    GameManager manager;
    
    /**
     * Constructs the game window. Constructing the game window starts the game.
     */
    public GameWindow(){
        setTitle("Life is Tough: Trader Tycoon");
        setSize(50, 50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        manager = new GameManager(this);
    }
}

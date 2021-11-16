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
 *
 * @author Luke Farris
 */
public class GameWindow extends JFrame {

    GameManager manager;
    
    public GameWindow(){
        setTitle("Life is Tough: Trader Tycoon");
        setSize(50, 50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        //manager = new GameManager(this);
        //manager.UpdateScreen();
    }
    
}

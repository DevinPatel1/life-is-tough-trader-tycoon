/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocksimfinal;

import javax.swing.JFrame;

/**
 *
 * @author luked
 */
public class GameWindow extends JFrame {

    GameManager manager;
    
    public GameWindow(){
        setTitle("StockSim");
        setSize(50, 50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        manager = new GameManager(this);
        //manager.UpdateScreen();
    }
    
}

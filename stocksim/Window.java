/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocksim;

import javax.swing.JFrame;

/**
 *
 * @author luked
 */
public class Window extends JFrame {
    
    private GameManager manager;
    
    public Window(){
        setTitle("StockSim");
        setSize(50, 50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        manager = new GameManager(this);
        manager.UpdateScreen();
    }
}

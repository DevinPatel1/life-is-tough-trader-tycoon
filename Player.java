/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tradertycoon;

/**
 *
 * @author shion
 */
public class Player {
    
    //Constructor
    //--------------------------------------------------------------------------
    public Player(String aName, Difficulties aDifficulty, int numberOfBusinesses) {
        
        aName = playerName;
        ownedShares = new int[numberOfBusinesses];
        
    }
    //--------------------------------------------------------------------------
    
    
    //Getter Methods
    //--------------------------------------------------------------------------
    public String getPlayerName() {
        return playerName;
    }
    
    public int getBank() {
        return bank;
    }
    
    public int getBusinessShares(int indexOfBusiness) {
        return ownedShares[indexOfBusiness];
    }
    //--------------------------------------------------------------------------
    
    //Updating data methods
    //--------------------------------------------------------------------------
    public void updateBank(int money) {
        bank += money;
    }
    
    public void updateShares(int indexOfBusiness, int shares) {
        ownedShares[indexOfBusiness] += shares;
    }
    //--------------------------------------------------------------------------
    
    //data
    //--------------------------------------------------------------------------
    private int bank;
    private String playerName;
    private int[] ownedShares;
    //--------------------------------------------------------------------------
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocksim;

/**
 *
 * @author luked
 */
public class Player {
    
    public enum Difficulty { Easy, Medium, Hard};
    
    private int bankAccount = 0;
    private final Difficulty gameDifficulty;
    
    private int currentWeek = 1;
    
    private String name;
    
    public Player(Difficulty diff, String name){
        gameDifficulty = diff;
        this.name = name;
    }
    
    public Difficulty getDifficulty(){
        return gameDifficulty;
    }
    
    public void addMoney(int cash){
        bankAccount += cash;
    }
    
    public int getBank(){
        return bankAccount;
    }
 
    public int getWeek() {
        return currentWeek;
    }
    
    public void incrementWeek() {
        currentWeek++;
    }
    
    public Player copy(){
        return new Player(gameDifficulty, name);
    }
    
    public String getName(){
        return name;
    }
}

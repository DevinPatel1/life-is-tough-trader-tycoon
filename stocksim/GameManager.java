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
public class GameManager {
    
    private JFrame window;
    private TitleScreen title = new TitleScreen(this);
    private StockScreen stockScreen;
    
    public enum GameState{Title, StockScreen, WeekScreen};

    private GameState currentState = GameState.Title;
    
    private Business businesses[];
    
    private Player player;
    
    public GameManager(JFrame window){
        this.window = window;
        GenerateBusinesses();
        stockScreen = new StockScreen(this);
    }
    
    public String GetPlayerName(){
        return player.getName();
    }
    
    private void GameTick() {
        
        switch(currentState){
            case Title:
                TitleGameTick();
                break;
            case StockScreen:
                StockScreenGameTick();
                break;
            case WeekScreen:
                WeekScreenGameTick();
                break;
            default:
                
                    
            
        }
        
    }
    
    public void UpdateScreen() {
        
        switch(currentState){
            case Title:
                window.setContentPane(title);
                window.pack();
                break;
            case StockScreen:
                window.setContentPane(stockScreen);
                window.pack();
                break;
            case WeekScreen:
                WeekScreenGameTick();
                break;
            default:
                
                    
            
        }
        
    }
    
    private void TitleGameTick(){
        
        
        
    }
    
    private void StockScreenGameTick() {
        
    }
    
    private void WeekScreenGameTick() {
        
    }
    
    private void OpenInstructionWindow(){
        
    }
    
    private void OpenWeekScreen(){
        
        currentState = GameState.WeekScreen;
    }
    
    public void StartGame(Player player){
        this.player = player.copy();
        stockScreen.setPlayerName();
        currentState = GameState.StockScreen;
        UpdateScreen();
        stockScreen.setWeek(player.getWeek());
    }
    
    public int GetPlayerBank(){
        return player.getBank();
    }
    
    private void GenerateBusinesses(){
        businesses = new Business[16];
        for (int i = 0; i < 16; i++){
            
            businesses[i] = new Business();
            
        }
        
    }
    
    public String[] GetListOfBusinessNames(){
     
        String listOfNames[] = new String[businesses.length];
        
        for(int i = 0; i < businesses.length; i++){
            listOfNames[i] = businesses[i].getCode();
        }
     
        return listOfNames;
        
    }
    
    public Business getBusiness(int i){
        return businesses[i];
    }
   
}

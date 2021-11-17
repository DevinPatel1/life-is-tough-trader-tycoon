/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

import java.util.Random;

/**
 * Game Manager is the interface between the GUI system and the game logic system.
 * 
 * @author Luke Farris
 */
public class GameManager {
    
    private Player player;
    private Business[] businesses;
    
    private ExpenseHandler expenses;
    private NewsEventHandler events;
    private GameWindow window;
    
    private StockScreen stockScreen;
    private NewsScreen newsScreen;
    private TitleScreen titleScreen;
    
    private GameState currentState;
    
    private int weekNumber;
    private int weeksBelowZero;
    
    public static final Random RNG = new Random();
    
    /**
     * Initializes the title screen and keeps a reference to the JFrame window.
     * @param aWindow The JFrame window that information will be pushed to
     */
    GameManager(GameWindow aWindow) {
        
        window = aWindow;
        titleScreen = new TitleScreen(this);
        
        window.setContentPane(titleScreen);
        window.pack();
    }

    /**
     * Initializes businesses and handlers based on the difficulty selected from the title screen.
     */
    public void startGame() {
        Difficulties difficulty = titleScreen.getDifficulty();
        
        // Initializes businesses array, each business in the array,
        //     news event and expense handlers, and the player
        businesses = new Business[6];
        initBusinesses();
        initHandlers(difficulty);
        player = new Player(titleScreen.getNameField(), difficulty, businesses.length);

        goToStockScreen();
    }
    
    /**
     * Accesses the player's name they specified on the title screen.
     * @return Player's name
     */
    public String getPlayerName() {
        return player.getPlayerName();
    }
    
    /**
     * Accesses the player's current bank balance.
     * @return Player's bank balance
     */
    public int getPlayerBank() {
        return player.getBank();
    }
    
    /**
     * Accesses the number of owned shares of the specified business.
     * @param symbol The symbol of the business
     * @return The number of shares from that business the player owns
     */
    public int getPlayerOwnedShares(BusinessSymbol symbol){
        return player.getBusinessShares(symbol);
    }
    
    /**
     * Generates a clone of the specified business.
     * @param symbol The symbol of the business
     * @return A copy of the business as a separate object
     */
    public Business getBusinessCopy(BusinessSymbol symbol) {
        return businesses[symbol.index].clone();
    }
    
    /**
     * Accesses the number of weeks progressed in the game.
     * @return Number of weeks transpired
     */
    public int getWeekNumber() {
        return weekNumber;
    }
    
    /**
     * Accesses the number of consecutive weeks the player has with a negative bank balance.
     * @return Number of weeks in the negatives
     */
    public int checkWeeksBelowZero() {
        return weeksBelowZero;
    }
    

    /**
     * Returns the cost based on the number of shares bought/sold.
     * @param symbol Stock symbol of the business
     * @param numberOfShares Number of shares to be used in calculation
     * @return Value of the shares
     */
    public int getCost(BusinessSymbol symbol, int numberOfShares) {
        // Ensures the number of shares are positive
        numberOfShares = Math.abs(numberOfShares);

        // Calculates cost of shares
        int totalCost = numberOfShares * businesses[symbol.index].getPrice();

        return totalCost;
    }

    /**
     * Buys the number of shares the player specifies according to the
     *     current price of the business.
     * @param symbol Stock symbol of the business
     * @param numberOfShares Number of shares to be bought
     * @param cost
     */
    public void buyShare(BusinessSymbol symbol, int numberOfShares) {
        // Calculates the cost of shares to buy
        int totalCost = getCost(symbol, numberOfShares);
        
        // Updates player's bank and shares
        player.updateBank(-1*totalCost);
        player.updateShares(symbol, numberOfShares);

        // Updates business's available shares
        businesses[symbol.index].changeSharesAvailable(-1*numberOfShares);
    }
    

    /**
     * Sells the number of shares the player specifies according to the
     *     current price of the business.
     * @param symbol Stock symbol of the business
     * @param numberOfShares Number of shares to be sold
     * @param cost
     */
    public void sellShare(BusinessSymbol symbol, int numberOfShares) {
        // Calculates cost of shares being sold
        int totalCost = getCost(symbol, numberOfShares);
        
        // Updates player's bank and shares
        player.updateBank(totalCost);
        player.updateShares(symbol, (-1*numberOfShares) );

        // Updates business's available shares
        businesses[symbol.index].changeSharesAvailable(numberOfShares);
    }
    
    // Sends the player to the News Screen
    private void goToNewsScreen(NewsEvent event, String aExpenseReason, int aExpenseAmount) {
        newsScreen = new NewsScreen(this, event, aExpenseReason, aExpenseAmount);

        currentState = GameState.NEWS_SCREEN;
        updateScreen();
    }
    
    /**
     * Sends the player to the previous week's news screen.
     * Nothing happens if the player has not progressed weeks yet.
     */
    public void goToPreviousNewsScreen() {
        if(newsScreen == null) return;
        currentState = GameState.NEWS_SCREEN;
        updateScreen();
    }

    /**
     * Sends the player to the stock screen.
     */
    public void goToStockScreen() {
        stockScreen = new StockScreen(this);
        currentState = GameState.STOCK_SCREEN;
        updateScreen();
    }
    

    /**
     * Progresses the game to the next week. Business prices are recalculated,
     *     News Events generate business fortune modifiers, and expenses are
     *     applied to the player's bank account. This information is displayed
     *     to the player on the News Screen. If the player's bank account is
     *     in the negatives for three weeks in a row, the player loses and the
     *     game ends.
     */
    public void goToNextWeek() {
        
        // Increments week number
        weekNumber++;

        // Checks for negative bank balance, resets weeks below zero if not negative
        if(getPlayerBank() < 0){
            weeksBelowZero++;
        }
        else {
            weeksBelowZero = 0;
        }
        
        // Checks to see if there has been more than 3 weeks with negative bank balance
        if(weeksBelowZero >= 3) {
            endGame();
        }

        // Generates expenses and news events and sends player to the weekly news screen
        NewsEvent event = events.generateEvent();
        
        Expense expense = expenses.generateExpense();
        String expenseReason = expense.getReason();
        int expenseAmount = expense.generateExpense();

        // Updates the player's bank account with expenses
        player.updateBank(expenseAmount);
        
        // Updates businesses and player bank
        generateBusinessWeeklyUpdate(event);

        // Generates the news screen based on news event and expense
        goToNewsScreen(event, expenseReason, expenseAmount);
    }
    
    /**
     * Sends the player to the title screen.
     */
    public void returnToTitleScreen() {
        
        //delete player?
        
        currentState = GameState.TITLE_SCREEN;
        updateScreen();
    }
    

    /**
     * Ends the game by disposing of the JFrame
     */
    public void endGame() {
        window.dispose();
    }

    // Updates business prices and fortunes whenever the player progresses to the next week
    private void generateBusinessWeeklyUpdate(NewsEvent event) {

        // Updates each businesses's price and fortune
        for(int i = 0; i < businesses.length; i++)
        {
            // Updates the price for each business before fortune modifier is applied
            businesses[i].generateNewPrice();

            // Updates the fortune of each business
            Tags[] businessTags = businesses[i].getTags();
            Tags[] eventTags = event.getTags();

            outerloop: // breaks out of the nested for loop once a matching tag is found
            for(Tags eTag : eventTags)
            {
                for(Tags bTag : businessTags)
                {
                    // Once a matching tag is found, the fortune is modified
                    if(bTag == eTag)
                    {
                        businesses[i].updateFortune((float) event.generateModifier());
                        break outerloop;
                    }
                }
            }
        }

    }
    
    // Pushes the JPanels to the JFrame
    private void updateScreen() {
        
        switch(currentState){
            case TITLE_SCREEN:
                window.setContentPane(titleScreen);
                window.pack();
                break;
            case STOCK_SCREEN:
                window.setContentPane(stockScreen);
                window.pack();
                break;
            case NEWS_SCREEN:
                window.setContentPane(newsScreen);
                window.pack();
                break;
            default:
        }
                
    }

    // Initializes NewsEventHandler and ExpenseHandler
    private void initHandlers(Difficulties difficulty)
    {
        events = new NewsEventHandler(difficulty);
        expenses = new ExpenseHandler(difficulty);
    }

    // Initializes each business in the array of businesses    
    private void initBusinesses()
    {
        // Business 1 - Farmers Mart (FRM)
        String name = "Farmers Mart";
        String description = "A farmer's market where people congregate and buy groceries and home goods.";
        int initialPrice = 100;
        int totalShares = 1000;
        Tags[] tags1 = {Tags.FOOD, Tags.SOCIAL, Tags.DOMESTIC_GOODS};
        businesses[BusinessSymbol.FRM.index] = new Business(name, description, initialPrice, totalShares, tags1);

        // Business 2 - Fuber (FUB)
        name = "Fuber";
        description = "An online food delivery service that relies on crowd-sourced drivers.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags2 = {Tags.FOOD, Tags.AUTOMOTIVE, Tags.INTERNET};
        businesses[BusinessSymbol.FUB.index] = new Business(name, description, initialPrice, totalShares, tags2);

        // Business 3 - Blockbuster (BUS)
        name = "Blockbuster";
        description = "A multinational online media streaming service.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags3 = {Tags.INTERNATIONAL, Tags.INTERNET};
        businesses[BusinessSymbol.BUS.index] = new Business(name, description, initialPrice, totalShares, tags3);

        // Business 4 - Winghut (WGH)
        name = "Winghut";
        description = "A sports bar chain that hosts large gatherings and serves food.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags4 = {Tags.FOOD, Tags.SOCIAL};
        businesses[BusinessSymbol.WGH.index] = new Business(name, description, initialPrice, totalShares, tags4);

        // Business 5 - United Postal Dudes (UPD)
        name = "United Postal Dudes";
        description = "A shipping company that relies on trucks and international travel.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags5 = {Tags.INTERNATIONAL, Tags.AUTOMOTIVE};
        businesses[BusinessSymbol.UPD.index] = new Business(name, description, initialPrice, totalShares, tags5);

        // Business 6 - iBay (BAY)
        name = "iBay";
        description = "An online multinational eCommerce platform that allows consumers to buy goods.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags6 = {Tags.INTERNATIONAL, Tags.INTERNET, Tags.DOMESTIC_GOODS};
        businesses[BusinessSymbol.BAY.index] = new Business(name, description, initialPrice, totalShares, tags6);
    }
        
}

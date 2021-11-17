/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

/**
 * Business represents one of six total businesses from 
 *     which the player is able to buys stocks from.<p>
 * Each business will keep track of its name, price,
 *     description, its price from the previous week,
 *     the number of shares left the player can buy,
 *     and the tags associated with the business.
 * 
 * @author Dominic Kenyon
 */
public class Business {

    /**
     * Constructs each business.
     * 
     * @param aName Name of the business
     * @param desc Description of the business
     * @param firstPrice Initial price of the stock
     * @param totalShares Amount of shares available to buy
     * @param aTags Event tags associated with the business
     */
    public Business(String aName, String desc, int firstPrice, int totalShares,
                    Tags[] aTags) {
        name = aName;
        description = desc;
        currentPrice = firstPrice;
        previousPrice = firstPrice;
        sharesAvailable = totalShares;
        fortune = 0;
        lowerBound = -10;
        upperBound = 10;
        tags = aTags;
    }
    
    /**
     * Accesses the current price of the business.
     * @return Current price of the business
     */
    public int getPrice(){
        return currentPrice;
    }
    

    /**
     * Accesses the previous week's price.
     * @return Previous week's price
     */
    public int getPreviousPrice(){
        return previousPrice;
    }
    

    /**
     * Accesses the number of shares left that can be bought.
     * @return Number of shares available
     */
    public int getSharesAvailable(){
        return sharesAvailable;
    }

    
    /**
     * Accesses the name of the business.
     * @return Name of the business
     */
    public String getName(){
        return name;
    }
    

    /**
     * Accesses the description of the business.
     * @return Description of the business
     */
    public String getDescription(){
        return description;
    }


    /**
     * Accesses the event tags of the business.
     * @return Tags of the business
     */
    public Tags[] getTags(){
        return tags;
    }
    

    /**
     * Generates a new stock price when the player progresses to next week.
     */
    public void generateNewPrice(){
        previousPrice = currentPrice;
        currentPrice += lowerBound + GameManager.RNG.nextInt(upperBound - lowerBound);
    }
    

    /**
     * Updates the fortune value of the business.
     * Fortune modifier values are calculated from a News Event.
     * @see NewsEvent
     * @param fortuneModifier Generated from a News Event
     */
    public void updateFortune(float fortuneModifier){
        fortune += fortuneModifier;
        
        upperBound += fortune;
        lowerBound += fortune;
    }

    
    /**
     * Adjusts the number of shares available depending on whether
     * the player buys or sells this particular stock.
     * @param changeOfShares Positive if selling, Negative if buying.
     */
    public void changeSharesAvailable(int changeOfShares){
        sharesAvailable += changeOfShares;
    }


    /**
     * Creates a deep copy of the business class.
     * @return Copy of the business as a separate object
     */
    public Business clone(){
        Tags[] clonedTags = new Tags[tags.length];

        for(int i = 0; i < tags.length ; i++){
            clonedTags[i] = tags[i];
        }

        Business clone = new Business(this.name, this.description, this.currentPrice, this.sharesAvailable, clonedTags);
        return clone;
    }


    /**
     * Accesses the array of tags associated with this business as a String
     * @return String of all the tags
     */
    public String getTagsAsString(){
        String s = tags[0].string;

        for(int i = 1; i < tags.length; i++){
            s += ", ";
            s += tags[i].string;
        }

        return s;
    }

    private int currentPrice;
    
    private int previousPrice;
    
    private int sharesAvailable;
    
    private int upperBound;
    
    private int lowerBound;
    
    private int fortune; // Gets initialized to 0 in constructor
    
    private final String name;
    
    private final String description;
    
    private final Tags[] tags;
}

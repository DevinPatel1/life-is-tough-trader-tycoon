/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

import java.util.ArrayList;

/**
 * Stores all random events and randomly chooses an event when the next week operation is initiated.
 * 
 * @author Devin Patel
 */
public class NewsEventHandler
{
    private ArrayList<NewsEvent> events = new ArrayList<>();

    /**
     * Constructs each event and their modifier ranges based on the difficulty selected.
     * @param aDifficulty Easy makes modifiers smaller, Hard makes modifiers larger.
     */
    public NewsEventHandler(Difficulties aDifficulty)
    {
        // Determines difficulty factor
        float diffFactor;

        if(aDifficulty == Difficulties.EASY)
            diffFactor = (float) 0.5;
        else if(aDifficulty == Difficulties.HARD)
            diffFactor = (float) 1.5;
        else
            diffFactor = (float) 1.0;

        // Adds each event
        addBlizzard(diffFactor);
        addOilReserveFound(diffFactor);
        addTradeEmbargo(diffFactor);
        addInteriorDesign(diffFactor);
        addCheaperTrading(diffFactor);
        addGovernmentSpying(diffFactor);
        addConfidenceClasses(diffFactor);
        addCloningDiscovery(diffFactor);
        add5GParanoia(diffFactor);
    }

    /**
     * Randomly selects an event that will be displayed at the end of the week.
     * @return A randomly chosen event
     */
    public NewsEvent generateEvent()
    {
        int chosenIndex = GameManager.RNG.nextInt(events.size());
        return events.get(chosenIndex);
    }
    

    // Blizzard Event - Negtively affects Shipping, Grocery, Sports Bar, Food Delivery
    private void addBlizzard(float factor)
    {
        String name = "Blizzard Blankets the US";
        String headline = "A devastating blizzard is sweeping over the nation.\n"
                        + "The roads are too hazardous to drive on and everyone is forced to stay indoors.";

        int lowerBound = Math.round(-10 * factor);
        int upperBound = Math.round(-1 * factor);
        
        Tags[] tags = {Tags.AUTOMOTIVE, Tags.SOCIAL};

        NewsEvent event = new NewsEvent(name, headline, lowerBound, upperBound, tags);
        events.add(event);
    }


    // Gas Prices Lowered - Positvely affects Shipping and Food Delivery
    private void addOilReserveFound(float factor)
    {
        String name = "New Oil Reserve Found";
        String headline = "A new oil reserve has been found in Siberia.\n"
                        + "Gas prices are booming!";
        
        int lowerBound = Math.round(5 * factor);
        int upperBound = Math.round(10 * factor);

        Tags[] tags = {Tags.AUTOMOTIVE};

        NewsEvent event = new NewsEvent(name, headline, lowerBound, upperBound, tags);
        events.add(event);
    }


    // Trade Embargo - Negatively affects all businesses except Shipping
    private void addTradeEmbargo(float factor)
    {
        String name = "Tradeland Mad at US";
        String headline = "One of the US's major trade partners placed a food embargo on the nation.\n"
                        + "And they seemed to have cut the intercontinental internet cable.\n";
        
        int lowerBound = Math.round(-15 * factor);
        int upperBound = Math.round(-5 * factor);

        Tags[] tags = {Tags.FOOD, Tags.INTERNET};

        NewsEvent event = new NewsEvent(name, headline, lowerBound, upperBound, tags);
        events.add(event);
    }


    // Interior Design Bill - Positively affects Grocery Store and Ecommerce
    private void addInteriorDesign(float factor)
    {
        String name = "President signs Interior Design Bill";
        String headline = "The President just signed a bill that forces everyone to have nice homes!\n"
                        + "Maybe now is a better time than any to get new decor and supplies...";
        
        int lowerBound = Math.round(3 * factor);
        int upperBound = Math.round(13 * factor);

        Tags[] tags = {Tags.DOMESTIC_GOODS};

        NewsEvent event = new NewsEvent(name, headline, lowerBound, upperBound, tags);
        events.add(event);
    }


    // Discounted Trading Partner - Positively affects Grocery Store, Shipping, Streaming, Ecommcerce.
    private void addCheaperTrading(float factor)
    {
        String name = "New Trade Partner Gives Discounts";
        String headline = "The US just helped a neighboring country gain independence, and as thanks, they are giving discounts on US-bound exports.\n"
                        + "These are just reverse tariffs!";
        
        int lowerBound = Math.round(17 * factor);
        int upperBound = Math.round(23 * factor);

        Tags[] tags = {Tags.INTERNATIONAL, Tags.DOMESTIC_GOODS};

        NewsEvent event = new NewsEvent(name, headline, lowerBound, upperBound, tags);
        events.add(event);
    }


    // Government Spying - Negatively affects Food Delivery, Ecommerce, and Streaming
    private void addGovernmentSpying(float factor)
    {
        String name = "Whistleblower Reveals Government Spying on Citizens";
        String headline = "An unknown man just leaked evidence that the government had been collecting tons of data on its citizens without their knowledge.\n"
                        + "Everyone is now paranoid, and for once, they are putting their phones down for a while.";
        
        int lowerBound = Math.round(-10 * factor);
        int upperBound = Math.round(-4 * factor);

        Tags[] tags = {Tags.INTERNET};

        NewsEvent event = new NewsEvent(name, headline, lowerBound, upperBound, tags);
        events.add(event);
    }


    // Confidence Classes - Positively affects Grocery Store and Sports Bar
    private void addConfidenceClasses(float factor)
    {
        String name = "Board of Education Approves of Self-Confidence Classes for Young Adults";
        String headline = "Young adults learning to build their self-esteem are now more outgoing and confident in public.\n"
                        + "Lines for restaurants and stores are getting waaaay longer.";
        
        int lowerBound = Math.round(7 * factor);
        int upperBound = Math.round(12 * factor);

        Tags[] tags = {Tags.SOCIAL};

        NewsEvent event = new NewsEvent(name, headline, lowerBound, upperBound, tags);
        events.add(event);
    }


    // Cloning Discovery - Positively affects Food
    private void addCloningDiscovery(float factor)
    {
        String name = "Scientists Make Groundbreaking Discovery in Cloning";
        String headline = "Biology researchers have finally discovered the copy/paste function, which has led to groundbreaking research in animal cloning.\n"
                        + "So much Ctrl + C and Ctrl + V pressing has led to sore fingers and a significant decrease in the price of meat.";
        
        int lowerBound = Math.round(20 * factor);
        int upperBound = Math.round(40 * factor);

        Tags[] tags = {Tags.FOOD};

        NewsEvent event = new NewsEvent(name, headline, lowerBound, upperBound, tags);
        events.add(event);
    }


    // 5G Paranoia - Negatively affects Automotive, Domestic Goods
    private void add5GParanoia(float factor)
    {
        String name = "Citizens Denounce the Use of Cars and Metal Tools";
        String headline = "A viral Facebook post circulated on the internet warning people that metal boxes and utensils intensify 5G radiation and could increase the risk of cancer.\n"
                        + "While this was proved to be a load of malarkey fairly quickly, this did not stop everyone from boycotting their cars and other metal possessions.";
        
        int lowerBound = Math.round(-10 * factor);
        int upperBound = Math.round(-5 * factor);

        Tags[] tags = {Tags.DOMESTIC_GOODS, Tags.AUTOMOTIVE};

        NewsEvent event = new NewsEvent(name, headline, lowerBound, upperBound, tags);
        events.add(event);
    }
}

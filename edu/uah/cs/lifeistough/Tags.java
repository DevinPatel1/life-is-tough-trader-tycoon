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
 * Each Business and NewsEvent class will have tags associated with them
 *     in order to delineate which event will affect which business when
 *     the player progresses to the next week.
 * 
 * @author Devin Patel
 * @author Luke Farris
 * @author Dominic Kenyon
 * @author Corey Clayborn
 * @author Ben Johnson
 */
public enum Tags
{
    INTERNET("Internet"),
    INTERNATIONAL("International"),
    AUTOMOTIVE("Automotive"),
    FOOD("Food"),
    SOCIAL("Social"),
    DOMESTIC_GOODS("Domestic Goods");

    public final String string;

    private Tags(String s)
    {
        this.string = s;
    }
}

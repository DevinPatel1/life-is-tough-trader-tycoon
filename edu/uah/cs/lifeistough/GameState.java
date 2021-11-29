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
 * The GameManager class needs to know which screen
 *     it is currently displaying to the player, so
 *     these values are being used to delineate
 *     which screen is being displayed.
 * 
 * @author Devin Patel
 * @author Luke Farris
 * @author Dominic Kenyon
 * @author Corey Clayborn
 * @author Ben Johnson
 */
public enum GameState
{
    TITLE_SCREEN,
    STOCK_SCREEN,
    NEWS_SCREEN;
}

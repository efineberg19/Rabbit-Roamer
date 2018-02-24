import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Runs the program
 * 
 * @author Beckwith and Beth Fineberg
 * @version 0.1
 */
public class RunnerGUI
{
    public static void main(String[] args)
    {
        Rabbit.show("Welcome to RabbitRoamer. The bunnies will move randomly and gain points"
            + " \nif they go into the middle of the grid or go into the same space as the coin."
            + " \nThey will lose a point if they go into the same space as the evil bunny. "
            + "\nThe first bunny to reach 20 points will win. There will be real time data "
            + "\ndisplayed on the right. There are buttons you can use to start, stop, and"
            + " \nchange the colors of the bunnies.");
        
        //the JFrame size:
        int windowWidth = 1100;
        int windowHeight = 700;

        int DELAY = 200;  //speed of graphics, lower number = faster
                          //(actually, the number of milliseconds between updates to graphics)
        
        //the display panel size within the JFrame:
        int displaySize = 600;

        int GRID_SIZE = 11;  //makes an nxn gridof squares within the 600 x 600 display
        //YOU COULD ASK THE USER WHAT SIZE GRID THEY WANT!!!
        
        int middleX = GRID_SIZE / 2;  //starting point for bunny
        int middleY = GRID_SIZE / 2;
        
        //size of 1 Bunny or 1 blank square:
        int squareSize = displaySize / GRID_SIZE; 
        //creates bunnies at middle:
        Rabbit bunny = new Rabbit(middleX, middleY, null, squareSize);
        Rabbit bunny2 = new Rabbit(middleX, middleY, null, squareSize);
        Rabbit point = new Rabbit(middleX, middleY, null, squareSize);
        Rabbit evilBunny = new Rabbit(middleX, middleY, null, squareSize);
        
        //make Frame, handing over Rabbit object to it
        Window win = new Window(windowWidth, windowHeight, 
                                GRID_SIZE, 
                                bunny, bunny2, point, evilBunny,
                                DELAY);

        //begin animation
        win.startTimer();
    }
}

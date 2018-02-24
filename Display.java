import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 * 
 * @author Beckwith & Beth Fineberg
 * @version 1.2.0
 */
public class Display extends JPanel
{
    Rabbit bunny, bunny2, point, evilBunny;
    int numSquaresAcross, col, row;  
    Font myFont; 
    BufferedImage image1 = null;
    BufferedImage image2 = null;
    BufferedImage image3 = null;
    BufferedImage image4 = null;
    BufferedImage image5 = null;
    BufferedImage image6 = null;
    BufferedImage image7 = null;
    BufferedImage image8 = null;
    BufferedImage image9 = null;
    double dist;
    int n = 0;
    int mid = 0;
    int score1 = 1;
    int score2 = 1;
    int numMoves = 0;
    
    /**
     * CONSTRUCTOR
     */
    public Display(Rabbit bunny, Rabbit bunny2, Rabbit point, Rabbit evilBunny, int w, int h, int numSquaresAcross) 
    {
        this.bunny = bunny;
        this.bunny2 = bunny2;
        this.point = point;
        this.evilBunny = evilBunny;
        this.numSquaresAcross = numSquaresAcross;

        this.myFont = new Font("Verdana", Font.BOLD, 16);  //nice font for display purposes
        setPreferredSize(new Dimension(w, h));  //set size of display       
    }

    /**
     * Draw text, grid, and rabbit; called in Window class by a timer calling repaint()
     * @param g the graphics object, automatically sent when repaint() is called
     */
    public void paintComponent(Graphics g) 
    {
        dist = Math.sqrt(Math.pow((bunny.xLoc - bunny2.xLoc), 2) + Math.pow((bunny.yLoc - bunny2.yLoc), 2));
        
        super.paintComponent(g); //clear the old drawings

        //use drawString show any updates or information:
        g.setFont(myFont);
        g.drawString("bunny1's number of moves: " + bunny.numMoves, 700, 15);  //text at position (650, 12)
        g.drawString("bunny2's number of moves: " + bunny2.numMoves, 700, 45);  //text at position (650, 12)
        g.drawString("number of times a bunny was in middle: \n" + mid, 700, 75);
        g.drawString("distance bunnies apart: " + dist, 700, 100);
        g.drawString("bunny1 coordinates: (" + bunny.xLoc + ", " + bunny.yLoc 
            + ")", 700, 125);
        g.drawString("bunny2 coordinates: (" + bunny2.xLoc + ", " + bunny2.yLoc 
            + ")", 700, 150);
        g.drawString("bunny1 score: " + score1, 700, 175);
        g.drawString("bunny2 score: " + score2, 700, 200);
        
        /*
         * show a yellow square in the middle:
         */
        g.setColor(Color.YELLOW);
        int middle = numSquaresAcross / 2;
        int SHIFT = 20;  //moves all squares down and to the right, so not at edge of display:
        
        g.fillRect(middle * bunny2.squareSize + SHIFT,middle * bunny2.squareSize + SHIFT,
            bunny2.squareSize,bunny2.squareSize);
        
        //go through field, showing rabbit or empty square:
        for(col = 0; col < numSquaresAcross; col++)
        {
            for(row = 0; row < numSquaresAcross; row++)
            {
                //draws rabbits and displays empty squares where the rabbits are not
                if(col == bunny.xLoc && row == bunny.yLoc) 
                    bunny.drawBunny1(g);  //draw the bunny
                else  //it must be square
                {
                    //set color of squares:
                    g.setColor(Color.GREEN);

                    //set location of where to draw current rectangle:
                    int x =  (int)(bunny.squareSize * col);
                    int y =  (int)(bunny.squareSize * row);

                    
                    //draw bunny rectangle:
                    g.drawRect(x + SHIFT , y + SHIFT, bunny.squareSize, bunny.squareSize);
                    
                }
                
                if(col == bunny2.xLoc && row == bunny2.yLoc) 
                    bunny2.drawBunny2(g);  //draw the bunny
                else  //it must be square
                {
                    //set color of squares:
                    g.setColor(Color.GREEN);

                    //set location of where to draw current rectangle:
                    int x =  (int)(bunny2.squareSize * col);
                    int y =  (int)(bunny2.squareSize * row);

                    //draw bunny rectangle:
                    g.drawRect(x + SHIFT , y + SHIFT, bunny2.squareSize, bunny2.squareSize);
                }
                
                if(col == point.xLoc && row == point.yLoc) 
                    {
                        point.drawPoint(g);  //draw the bunny
                    }
                else  //it must be square
                {
                    //set color of squares:
                    g.setColor(Color.GREEN);

                    //set location of where to draw current rectangle:
                    int x =  (int)(point.squareSize * col);
                    int y =  (int)(point.squareSize * row);

                    //draw bunny rectangle:
                    g.drawRect(x + SHIFT , y + SHIFT, point.squareSize, point.squareSize);
                }
                
                if(col == evilBunny.xLoc && row == evilBunny.yLoc) 
                    {
                        evilBunny.drawEvilBunny(g);  //draw the bunny
                    }
                else  //it must be square
                {
                    //set color of squares:
                    g.setColor(Color.GREEN);

                    //set location of where to draw current rectangle:
                    int x =  (int)(point.squareSize * col);
                    int y =  (int)(point.squareSize * row);

                    //draw bunny rectangle:
                    g.drawRect(x + SHIFT , y + SHIFT, point.squareSize, point.squareSize);
                }
            }
        }
        
        //if bunny is in middle, adds to counter of how many times in the middle and adds
        //to score
        if (bunny.xLoc == middle && bunny.yLoc == middle)
        {
            mid++;
            score1++;
        }
        if (bunny2.xLoc == middle && bunny2.yLoc == middle)
        {
            mid++;
            score2++;
        }
            
        //if bunny is in same square as the point, it adds to the bunny's score
        if (bunny.xLoc == point.xLoc && bunny.yLoc == point.yLoc)
        {
            score1++;
        }
        if (bunny2.xLoc == point.xLoc && bunny2.yLoc == point.yLoc)
        {
            score2++;
        }
        
        //if bunny is in same square as the evilBunny, it subtracts from the bunny's score
        if (bunny.xLoc == evilBunny.xLoc && bunny.yLoc == evilBunny.yLoc)
        {
            score1--;
        }
        if (bunny2.xLoc == evilBunny.xLoc && bunny2.yLoc == evilBunny.yLoc)
        {
            score2--;
        }
        
        //shows alert and ends game if both bunnies have a score of 0
        if (score1 == 0 && score2 == 0)
        {
            Rabbit.show("Both your bunnies are dead! Game over.");
            System.exit(0);
        }
        
        //shows alert and ends game when bunny reaches the score of 20
        if (score1 == 20)
        {
            score1++;
            Rabbit.show("Bunny1 has won!");
            System.exit(0);
        }
        if (score2 == 20)
        {
            score2++;
            Rabbit.show("Bunny2 has won!");
            System.exit(0);
        }
    }
}

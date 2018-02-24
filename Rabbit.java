import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 * Creates methods and parameters for rabbits
 * 
 * @author Beth Fineberg
 * @version 1.0
 */

public class Rabbit
{
    int xLoc, yLoc, numMoves, squareSize;
    Color color;
    
    BufferedImage image1 = null;
    BufferedImage image2 = null;
    BufferedImage image3 = null;
    BufferedImage image4 = null;
    BufferedImage image5 = null;
    BufferedImage image6 = null;
    BufferedImage image7 = null;
    BufferedImage image8 = null;
    BufferedImage image9 = null;
    
    /**
     * constructor
     * 
     * @param x x-location of the rabbit
     * @param y y-location of the rabbit
     * @param c color of the rabbit
     * @param sqSize the size of the rabbit's square
     */
    public Rabbit(int x, int y, Color c, int sqSize)
    {
        xLoc = x;
        yLoc = y;
        color = c;
        squareSize = sqSize;
        
        try
        {
            image1 = ImageIO.read(this.getClass().getResourceAsStream("download.png"));
            image2 = ImageIO.read(this.getClass().getResourceAsStream("bunny.png"));
            image3 = ImageIO.read(this.getClass().getResourceAsStream("blue bunny.png"));
            image4 = ImageIO.read(this.getClass().getResourceAsStream("green bunny.png"));
            image5 = ImageIO.read(this.getClass().getResourceAsStream("purple bunny.png"));
            image6 = ImageIO.read(this.getClass().getResourceAsStream("yellow bunny.png"));
            image7 = ImageIO.read(this.getClass().getResourceAsStream("red bunny.png"));
            image8 = ImageIO.read(this.getClass().getResourceAsStream("monty python bunny.png"));
            image9 = ImageIO.read(this.getClass().getResourceAsStream("invisible.png"));
        } catch (Exception e) {}
    }
    
    /*
     * Set Color
     * 
     * @param c2 color to change rabbit to
     * @return n/a
     */
    public void setColor(Color c2)
    {
        color = c2;
    }

    /*
     * Move
     * 
     * @param moveCode which case (direction) for rabbit to move
     * @return n/a
     */
    public void move(int moveCode)
    {
        switch(moveCode)
        {
            case 0: //north
                yLoc--;
                numMoves++;
            break;
            
            case 1: //north east
                xLoc++;
                yLoc--;
                numMoves++;
            break;
            
            case 2: //south
                yLoc++;
                numMoves++;
            break;
            
            case 3: //south east
                xLoc++;
                yLoc++;
                numMoves++;
            break;
            
            case 4: //south west
                xLoc--;
                yLoc++;
                numMoves++;
            break;
            
            case 5: //north west
                xLoc--;
                yLoc--;
                numMoves++;
            break;
            
            case 6: //west
                xLoc--;
                numMoves++;
            break;
            
            case 7: //east
                xLoc++;
                numMoves++;
            break;
            
            default:
                numMoves++;
            break;
        }
    }

    /*
     * Draw
     * 
     * @param g where the rabbit will be drawn
     * @param d the display which the rabbit is in
     * @return n/a
     */
    public void draw(Graphics g, Display d)
    {
        g.setColor(color);   //sets the color of this rabbit before drawing
        
        final int SHIFT = 20;  //shifts right and down to match where grid is positioned
        //location of this Rabbit on grid:
        int x =  (int)(squareSize * xLoc) + SHIFT;  
        int y =  (int)(squareSize * yLoc) + SHIFT;
        
        //draw the rabbit square:
        g.fillRect(x, y, squareSize, squareSize);  //rectangle with equal w and h is a square located at (x,y)
    }
    
    /*
     * Draw Point
     * Draws the coin
     * 
     * @param g where the point rabbit will be drawn
     * @return n/a
     */
    public void drawPoint(Graphics g)
    {
        final int SHIFT = 30;
        
        int x =  (int)(squareSize * xLoc) + SHIFT;  
        int y =  (int)(squareSize * yLoc) + SHIFT;
        
        g.drawImage(image1, x, y, null);
    }
    
     /*
     * Draw Bunny1
     * Draws the first rabbit
     * 
     * @param g where the first rabbit will be drawn
     * @return n/a
     */
    public void drawBunny1(Graphics g)
    {
        final int SHIFT = 20;
        
        int x =  (int)(squareSize * xLoc) + 30;  
        int y =  (int)(squareSize * yLoc) + SHIFT;
        
        if(color == null)
        {
            g.drawImage(image2, x, y, null);
        }
        if (color == Color.BLUE)
        {
            g.drawImage(image3, x, y, null);
        }
        if (color == Color.GREEN)
        {
            g.drawImage(image4, x, y, null);
        }
        if (color == Color.MAGENTA)
        {
            g.drawImage(image5, x, y, null);
        }
        if (color == Color.YELLOW)
        {
            g.drawImage(image6, x, y, null);
        }
        if (color == Color.RED)
        {
            g.drawImage(image7, x, y, null);
        }
    }
    
     /*
     * Draw Bunny2
     * Draws the second rabbit
     * 
     * @param g where the second rabbit will be drawn
     * @return n/a
     */
    public void drawBunny2(Graphics g)
    {
        final int SHIFT = 20;
        
        int x =  (int)(squareSize * xLoc) + 30;  
        int y =  (int)(squareSize * yLoc) + SHIFT;
        
        if(color == null)
        {
            g.drawImage(image2, x, y, null);
        }
        if (color == Color.BLUE)
        {
            g.drawImage(image3, x, y, null);
        }
        if (color == Color.GREEN)
        {
            g.drawImage(image4, x, y, null);
        }
        if (color == Color.MAGENTA)
        {
            g.drawImage(image5, x, y, null);
        }
        if (color == Color.YELLOW)
        {
            g.drawImage(image6, x, y, null);
        }
        if (color == Color.RED)
        {
            g.drawImage(image7, x, y, null);
        }
    }
    
     /*
     * Draw Evil Bunny
     * Draws the evilBunny rabbit
     * 
     * @param g where the evilBunny rabbit will be drawn
     * @return n/a
     */
    public void drawEvilBunny(Graphics g)
    {
        final int SHIFT = 20;
        
        int x =  (int)(squareSize * xLoc) + SHIFT;  
        int y =  (int)(squareSize * yLoc) + SHIFT;
        
        g.drawImage(image8, x, y, null);
    }
    
    /*
     * Show
     * Creates a JOptionPane to display text
     * 
     * @param text words displayed
     * @return n/a
     */
    public static void show (String text)
    {
        JOptionPane.showMessageDialog(null, text);
    }
}

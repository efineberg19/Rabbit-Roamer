import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JComboBox;

/**
 * Provides a window for buttons, entry boxes and displaying of grahpics
 * @author Beckwith & Beth Fineberg
 * @version 1.2.0
 */
public class Window extends JFrame implements ActionListener
{
    int TIMER_DELAY;  //# milliseconds between updates of graphics 
    Timer t;

    //components and objects:
    JButton pause, start, quit;
    JPanel topPanel, displayPanel;
    JTextField info;
    Display display;
    Rabbit bunny, bunny2, point, evilBunny;

    int numSquaresAcross;
    //size of window:
    int WIDTH;
    int HEIGHT;
    
    int colorPicked1 = 0;
    JComboBox<String> colorPick1;
    
    int colorPicked2 = 0;
    JComboBox<String> colorPick2;
    
    /**
     * Constructor for Window
     */
    public Window(int width, int height, int numSquaresAcross, Rabbit  bunny, 
        Rabbit bunny2, Rabbit point, Rabbit evilBunny, int delay)
    {
        //in case you're wondering: this calls the constructor of the "super class", which
        //   is JFrame.  That constructor takes the titlebar name as an argument:
        super("Rabbit Roamer");
        
        TIMER_DELAY = delay;
        //save instance variables:
        WIDTH = width;
        HEIGHT = height;
        this.numSquaresAcross = numSquaresAcross;
        this.bunny = bunny;
        this.bunny2 = bunny2;
        this.point = point;
        this.evilBunny = evilBunny;

        //this allows me to place things where I want them (see this.add below)
        this.setLayout(new BorderLayout());

        //pauses movement
        pause = new JButton("Pause");
        pause.addActionListener(this);
        pause.setActionCommand("Pause");
        
        //starts movement
        start = new JButton("Start");
        start.addActionListener(this);
        start.setActionCommand("Start");
        
        //quits the game
        quit = new JButton("Quit");
        quit.addActionListener(this);
        quit.setActionCommand("Quit");

        //color pick button for bunny 1
        String[] colors ={"Set bunny1's color", "Red", "Yellow", "Green", "Blue", "Magenta"};
        
        colorPick1 = new JComboBox<String>(colors);
        colorPick1.setSelectedIndex(0);
        colorPick1.addActionListener(this);
        colorPick1.setActionCommand("colorPick1");
        
        //color pick button for bunny2
        String[] colors2 ={"Set bunny2's color", "Red", "Yellow", "Green", "Blue", "Magenta"};
        
        colorPick2 = new JComboBox<String>(colors2);
        colorPick2.setSelectedIndex(0);
        colorPick2.addActionListener(this);
        colorPick2.setActionCommand("colorPick2");
        
        //panel for buttons and textfields:
        topPanel = new JPanel();
        topPanel.add(quit);
        topPanel.add(start);
        topPanel.add(pause);
        topPanel.add(colorPick1);
        topPanel.add(colorPick2);

        //add a text field for getting stuff from user, if needed
        info = new JTextField(20);
        topPanel.add(info);

        //make a display object that will show board and motion:
        display = new Display(bunny, bunny2, point, evilBunny, WIDTH, HEIGHT, numSquaresAcross);

        //add the display to this JFrame:
        this.add(topPanel, BorderLayout.NORTH);
        this.add(display,     BorderLayout.CENTER);

        //final setup
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));  
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Creates a Timer object and starts timer
     */
    public void startTimer()
    {
        t = new Timer(TIMER_DELAY, this); 
        t.setActionCommand("timerFired");
        t.start();
    }

    /**
     * Called automatically when a button is pressed
     * @param e this contains information about the button that was pressed and is send automatically
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Quit"))  //button has been pressed
        {
            System.exit(0);
        }
        
        if(e.getActionCommand().equals("Pause"))  //button has been pressed
        {
            t.stop();
        }
        
        if(e.getActionCommand().equals("Start"))  //button has been pressed
        {
            t.start();
        }
        
        if(e.getActionCommand().equals("timerFired"))  //timer has fired
        {
            updateAll();            //does all motion and checking and logic (see below)
            display.repaint();      //calls paintComponent to redraw everything
        }
        
        if(e.getActionCommand() == "colorPick1")
        {
            colorPicked1 = colorPick1.getSelectedIndex();
            
            switch (colorPicked1)
            {
                case 1:
                {
                    bunny.setColor(Color.RED);
                }
                break;
                
                case 2:
                {
                    bunny.setColor(Color.YELLOW);
                }
                break;
                
                case 3:
                {
                    bunny.setColor(Color.GREEN);
                }
                break;
                
                case 4:
                {
                    bunny.setColor(Color.BLUE);
                }
                break;
                
                case 5:
                {
                    bunny.setColor(Color.MAGENTA);
                }
                break;
            }
        }
        
        if(e.getActionCommand() == "colorPick2")
        {
            colorPicked2 = colorPick2.getSelectedIndex();
            
            switch (colorPicked2)
            {
                case 1:
                {
                    bunny2.setColor(Color.RED);
                }
                break;
                
                case 2:
                {
                    bunny2.setColor(Color.YELLOW);
                }
                break;
                
                case 3:
                {
                    bunny2.setColor(Color.GREEN);
                }
                break;
                
                case 4:
                {
                    bunny2.setColor(Color.BLUE);
                }
                break;
                
                case 5:
                {
                    bunny2.setColor(Color.MAGENTA);
                }
                break;
            }
        }
    }

    /**
     * Called by the timer.
     * Do all altering of bunnies, etc. here, since this is followed by a repaint of everything
     */
    public void updateAll()
    {
        int moveCode = (int)(Math.random() * 8);
        bunny.move(moveCode);   //update location
        
        int moveCode2 = (int)(Math.random() * 8);
        bunny2.move(moveCode2);
        
        int moveCode3 = (int)(Math.random() * 8);
        point.move(moveCode3);
        
        int moveCode4 = (int)(Math.random() * 8);
        evilBunny.move(moveCode4);
       
        //if off an edge, recenter:
        if(bunny.xLoc < 0 || bunny.xLoc >= numSquaresAcross) bunny.xLoc = numSquaresAcross / 2;
        if(bunny.yLoc < 0 || bunny.yLoc >= numSquaresAcross) bunny.yLoc = numSquaresAcross / 2;

        if(bunny2.xLoc < 0 || bunny2.xLoc >= numSquaresAcross) bunny2.xLoc = numSquaresAcross / 2;
        if(bunny2.yLoc < 0 || bunny2.yLoc >= numSquaresAcross) bunny2.yLoc = numSquaresAcross / 2;
        
        if(point.xLoc < 0 || point.xLoc >= numSquaresAcross) point.xLoc = numSquaresAcross / 2;
        if(point.yLoc < 0 || point.yLoc >= numSquaresAcross) point.yLoc = numSquaresAcross / 2;

        if(evilBunny.xLoc < 0 || evilBunny.xLoc >= numSquaresAcross) evilBunny.xLoc = numSquaresAcross / 2;
        if(evilBunny.yLoc < 0 || evilBunny.yLoc >= numSquaresAcross) evilBunny.yLoc = numSquaresAcross / 2;

        
        //DO ALL OTHER LOGIC HERE: MAYBE CHECKING OTHER BUNNIES OR FOR COLLISION, ETC.
    
        if(bunny.xLoc < 0 || bunny.xLoc >= numSquaresAcross) bunny.xLoc = numSquaresAcross / 2;
        if(bunny2.xLoc < 0 || bunny2.xLoc >= numSquaresAcross) bunny2.xLoc = numSquaresAcross / 2;
        if(point.xLoc < 0 || point.xLoc >= numSquaresAcross) point.xLoc = numSquaresAcross / 2;
        if(evilBunny.xLoc < 0 || evilBunny.xLoc >= numSquaresAcross) evilBunny.xLoc = numSquaresAcross / 2;
    }
}

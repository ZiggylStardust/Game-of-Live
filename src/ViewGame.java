import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class represts Window in which a version of the game runs
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 27/04/18
 */
public class ViewGame extends JInternalFrame implements ActionListener {
    static int nr = 0, xpos = 30, ypos = 30;
    AnzeigeFlaeche myView;
    private Color dead=Color.GREEN;                 //saves the colors
    private Color alive=Color.RED;
    boolean isFigure=false;                         //is a figure being set
    boolean [][] figure={{false}};                  //saves figure
    private BoardView boardView;


    private GameOfLife game;
    JMenuBar menuBar = new JMenuBar();
    JMenu[] menus = { new JMenu("Modus"), // Array mit 3 Menues
            new JMenu("Speed"), new JMenu("Fenster") , new JMenu("Figur")};
    JMenuItem[] items ={new JMenuItem("Run/Pause"),new JMenuItem("Set"),new JMenuItem("Paint"),
            new JMenuItem("Fast"),new JMenuItem("Medium"),new JMenuItem("Slow"),
            new JMenuItem("new Window"),new JMenuItem("Change Color Alive"),new JMenuItem("Change Color Dead"), new JMenuItem("FlipX"), new JMenuItem("FlipY"),
            new JMenuItem("Glider"),new JMenuItem("f-Pentomino"),new JMenuItem("Blinker"), new JMenuItem("Biploe"), new JMenuItem("Clear")};

    /**
     * Alterante Construktor
     * @param myView        refrence to ViewGame
     * @param game          reference to GameOfLife
     * @param game   Reference to booleans
     * @param thread        reference to Thread
     */
    public ViewGame(AnzeigeFlaeche myView, GameOfLife game){
        super ("Game " + (++nr), true, true);
        this.myView=myView;
        this.game=game;
        this.game=game;
        for (int i = 0; i < items.length; i++) { // fuer alle Eintraege:
            menus[(i<3)?0:(i<6)?1:(i<10)?2:3].add(items[i]); // add Items in Menue 0|1|2
            items[i].addActionListener(this);
        }
        for (int i = 0; i < menus.length; i++) // fuer alle Menues:
            menuBar.add (menus[i]); // fuege ein in Menue-Leiste

        boardView = new BoardView(game, this);
        add(boardView);
        game.addObserver(boardView);

        setJMenuBar (menuBar);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toString()){

            case "Run/Pause":{                      //pauses or starts the game
                game.isRun=!game.isRun;
                game.isPaint=false;
                game.isSet=false;
                break;
            }
            case "Set":{                               //enables the option to set cells to alive
                game.isRun=false;               //pauses the game while setting
                game.isSet=true;
                isFigure=false;                        //disables setting figures
                break;
            }
            case "Paint":{                                 //enables paint
                game.isRun=false;
                game.isPaint=true;
                isFigure=false;
                break;
            }
            case "Fast":{                                   //changes speed
                game.setSpeed(100);
                break;
            }
            case "Medium":{
                game.setSpeed(1000);
                break;
            }
            case "Slow":{
                game.setSpeed(2000);
                break;
            }
            case "new Window":{                         //opens new window
                ViewGame viewGame1 = new ViewGame(AnzeigeFlaeche.desktop, game); //passes refernce to thread and the boolean values
                BoardView boardView1 = new BoardView(game, viewGame1);
                viewGame1.add(boardView1);
                game.addObserver(boardView1);
                AnzeigeFlaeche.desktop.addChild (viewGame1, xpos+=20, ypos+=20);
                break;
            }
            case "Change Color Alive": {                            //changes color of living Cells
                alive=JColorChooser.showDialog(this,"Select living color",Color.RED);
                break;
            }
            case "Change Color Dead":{                                 //changes color of dead cells
                dead=JColorChooser.showDialog(this,"Select dead color",Color.GREEN);
                break;
            }
            case "FlipX":{                                       //flips on the y axis (left is right)
                boardView.flipX=!boardView.flipX;
                boardView.remapButtons();


                break;

            }
            case "FlipY":{                                       //flips on the y axis (left is right)
                boardView.setFlipY(!boardView.isFlipY());
                boardView.remapButtons();


                break;
            }    //set figures on grid
            case "Glider":
                game.isPaint=false;
                game.isSet=false;
                isFigure=true;
                figure=KonstruktionsFeld.getForm(Konstruktionen.GLEITER);
                break;
            case "f-Pentomino":{
                game.isPaint=false;
                game.isSet=false;
                isFigure=true;
                figure=KonstruktionsFeld.getForm(Konstruktionen.F_PENTOMINO);
                break;
                }
            case "Blinker":{
                game.isPaint=false;
                game.isSet=false;
                isFigure=true;
                figure=KonstruktionsFeld.getForm(Konstruktionen.BLINKER);
                break;
            }
            case "Biploe":{
                game.isPaint=false;
                game.isSet=false;
                isFigure=true;
                figure=KonstruktionsFeld.getForm(Konstruktionen.BIPLOE);
                break;
            }
            case "Clear":{game.resetFeld(); break;}         //clears game, kills all cells

        }
    }

    public Color getAlive() {
        return alive;
    }

    public Color getDead() {
        return dead;
    }

    public boolean[][] getFigure() {
        return figure;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }
}

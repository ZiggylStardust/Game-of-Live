import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewGame extends JInternalFrame implements ActionListener {
    static int nr = 0, xpos = 30, ypos = 30;
    AnzeigeFlaeche desktop;
    boolean isPaint=false;
    boolean isSet=false;
    // boolean isRun=false;
    private Color dead=Color.GREEN;
    private Color alive=Color.RED;
    boolean isFigure=false;
    boolean [][] figure={{false}};
    boolean flip=false;
    BoardView boardView;


    private GameOfLife game;
    JMenuBar menuBar = new JMenuBar();
    JMenu[] menus = { new JMenu("Modus"), // Array mit 3 Menues
            new JMenu("Speed"), new JMenu("Fenster") , new JMenu("Figur")};
    JMenuItem[] items ={new JMenuItem("Run/Pause"),new JMenuItem("Set"),new JMenuItem("Paint"),
            new JMenuItem("Fast"),new JMenuItem("Medium"),new JMenuItem("Slow"),
            new JMenuItem("new Window"),new JMenuItem("Change Color Alive"),new JMenuItem("Change Color Dead"), new JMenuItem("Flip"),
            new JMenuItem("Glider"),new JMenuItem("f-Pentomino"),new JMenuItem("Blinker"), new JMenuItem("Biploe"), new JMenuItem("Clear")};

    public ViewGame(AnzeigeFlaeche desktop, GameOfLife game){
        super ("Game " + (++nr), true, true);
        this.desktop = desktop;
        this.game = game;
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

            case "Run/Pause":{
                game.isRun=! game.isRun;
                isPaint=false;
                isSet=false;
                break;
            }
            case "Set":{
                game.isRun=false;
                isSet=true;
                    break;
            }
            case "Paint":{
                game.isRun=false;
                isPaint=true;
                break;
            }
            case "Fast":{
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
            case "new Window":{
                ViewGame viewGame1 = new ViewGame(AnzeigeFlaeche.desktop, game);
                BoardView boardView1 = new BoardView(game, viewGame1);
                viewGame1.add(boardView1);
                game.addObserver(boardView1);
                AnzeigeFlaeche.desktop.addChild (viewGame1, xpos+=20, xpos+=20);
                break;
            }
            case "Change Color Alive": {
                alive=JColorChooser.showDialog(this,"Select living color",Color.RED);
                break;
            }
            case "Change Color Dead":{
                dead=JColorChooser.showDialog(this,"Select dead color",Color.GREEN);
                break;
            }
            case "Flip":{
                flip=!flip; break;
            }
            case "Glider":
                isPaint=false;
                isSet=false;
                isFigure=true;
                figure=KonstruktionsFeld.getForm(Konstruktionen.GLEITER);
                break;
            case "f-Pentomino":{
                isPaint=false;
                isSet=false;
                isFigure=true;
                figure=KonstruktionsFeld.getForm(Konstruktionen.F_PENTOMINO);
                break;
                }
            case "Blinker":{
                isPaint=false;
                isSet=false;
                isFigure=true;
                figure=KonstruktionsFeld.getForm(Konstruktionen.BLINKER);
                break;
            }
            case "Biploe":{
                isPaint=false;
                isSet=false;
                isFigure=true;
                figure=KonstruktionsFeld.getForm(Konstruktionen.BIPLOE);
                break;
            }
            case "Clear":{game.resetFeld(); break;}

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
}

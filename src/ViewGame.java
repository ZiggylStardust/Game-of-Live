import javax.swing.*;
import java.awt.*;

public class ViewGame extends JInternalFrame {
    static int nr = -1, xpos = 30, ypos = 30;
    AnzeigeFlaeche myView;
    private GameOfLife game=new GameOfLife(32,32);
    JMenuBar menuBar = new JMenuBar();
    JMenu[] menus = { new JMenu("Modus"), // Array mit 3 Menues
            new JMenu("Speed"), new JMenu("Fenster") , new JMenu("Figur")};
    JMenuItem[] items ={new JMenuItem("Run"),new JMenuItem("Set"),new JMenuItem("Paint"),
            new JMenuItem("Fast"),new JMenuItem("Medium"),new JMenuItem("Slow"),
            new JMenuItem("new Window"),new JMenuItem("ChangeColorLive"),new JMenuItem("ChangeColorDead"),
            new JMenuItem("Glider"),new JMenuItem("f-Pentomino"),new JMenuItem("Blinker"), new JMenuItem("Biploe")};

    public ViewGame(AnzeigeFlaeche myView){
        super ("Game " + (++nr), true, true);
        this.myView=myView;
        /*Container cp = getContentPane();
        cp.setLayout(new GridLayout(game.getLength(),game.getHeight()));
        for (int i = 0; i < items.length; i++) { // fuer alle Eintraege:
            menus[(i<3)?0:(i<6)?1:(i<9)?2:3].add(items[i]); // add Items in Menue 0|1|2
        }*/
        for (int i = 0; i < menus.length; i++) // fuer alle Menues:
            menuBar.add (menus[i]); // fuege ein in Menue-Leiste
        setJMenuBar (menuBar);
        setVisible(true);
    }
}

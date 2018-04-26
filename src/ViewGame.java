import javax.swing.*;
import java.awt.*;

public class ViewGame extends JInternalFrame {
    static int nr = -1, xpos = 30, ypos = 30;
    AnzeigeFlaeche myView;
    private GameOfLife game=new GameOfLife(32,32);
    JMenuBar menuBar = new JMenuBar();
    JMenu[] menus = { new JMenu("Modus"), // Array mit 3 Menues
            new JMenu("Speed"), new JMenu("Fenster") , new JMenu("Figur")};
    JMenuItem[] items ={new JMenuItem("Run"),new JMenuItem("Set"),new JMenuItem("Paint"),}
    public ViewGame(AnzeigeFlaeche myView){
        super ("Game " + (++nr), true, true);
        this.myView=myView;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(game.getLenght(),game.getHight()));

    }
}

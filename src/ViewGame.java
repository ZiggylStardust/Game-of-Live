import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewGame extends JInternalFrame implements ActionListener {
    static int nr = 0, xpos = 30, ypos = 30;
    AnzeigeFlaeche myView;
    private GameOfLife game=new GameOfLife(32,32);

    JMenuBar menuBar = new JMenuBar();
    JMenu[] menus = { new JMenu("Modus"), // Array mit 3 Menues
            new JMenu("Speed"), new JMenu("Fenster") , new JMenu("Figur")};
    JMenuItem[] items ={new JMenuItem("Run"),new JMenuItem("Set"),new JMenuItem("Paint"),
            new JMenuItem("Fast"),new JMenuItem("Medium"),new JMenuItem("Slow"),
            new JMenuItem("new Window"),new JMenuItem("ChangeColorLive"),new JMenuItem("ChangeColorDead"), new JMenuItem("Flip"),
            new JMenuItem("Glider"),new JMenuItem("f-Pentomino"),new JMenuItem("Blinker"), new JMenuItem("Biploe")};

    public ViewGame(AnzeigeFlaeche myView){
        super ("Game " + (++nr), true, true);
        this.myView=myView;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(game.getLength(),game.getHeight()));
        for (int i = 0; i < items.length; i++) { // fuer alle Eintraege:
            menus[(i<3)?0:(i<6)?1:(i<10)?2:3].add(items[i]); // add Items in Menue 0|1|2
            items[i].addActionListener(this);
        }
        for (int i = 0; i < menus.length; i++) // fuer alle Menues:
            menuBar.add (menus[i]); // fuege ein in Menue-Leiste
        setJMenuBar (menuBar);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toString()){
            case "Run":{} //TODO: implement Threads
            case "Set":{}
            case "Paint":{}
            case "Fast":{}
            case "Medium":{}
            case "Slow":{}
            case "new Window": AnzeigeFlaeche.desktop.addChild (new ViewGame (AnzeigeFlaeche.desktop), xpos+=20, xpos+=20); break;
            case "ChangeColorLive": {}
            case "Flip":{}
            case "Glider":{}
            case "f-Pentomino":{}
            case "Blinker":{}
            case "Biploe":{}

        }
    }
}

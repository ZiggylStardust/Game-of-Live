import javax.swing.*;

/**
 * Class to represent the desktop
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 27/04/18
 */
public class DesktopWindow extends JFrame{
     static JDesktopPane desk;
    static DesktopWindow desktop = new DesktopWindow(); // Hauptfenster erzeugen

    public DesktopWindow(){
        desk = new JDesktopPane(); // neue DesktopPane
        desk.setDesktopManager (new DefaultDesktopManager()); // mit neuem Manager
        setContentPane (desk); // als neue ContentPane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960,1080 ); setLocation(0, 0);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);Ok
        GameOfLife game = new GameOfLife(5, 10, Construction.GLIDER);  //game created with Gleiter Figure
        ViewGame viewGame = new ViewGame(DesktopWindow.desktop, game);
        addChild(viewGame, 50, 50);

        setTitle("Game Of Life");
        setVisible(true);
    }
    public void addChild (JInternalFrame child, int x, int y) { // Hinzufuegen
        child.setLocation (x, y); // Ort und
        child.setSize (500 , 500); // Groesse setzen
        child.setDefaultCloseOperation (JInternalFrame.DISPOSE_ON_CLOSE); // Schiessoperation
        desk.add (child); // Kindfenster einfuegenchild.setVisible (true); // und sichtbar machen
    }
    public static void main (String[] args) {
    }

}
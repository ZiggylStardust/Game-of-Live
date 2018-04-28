import javax.swing.*;

/**
 * Class to represent the desktop
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 27/04/18
 */
public class AnzeigeFlaeche extends JFrame{
    private static JDesktopPane desk;
    static AnzeigeFlaeche desktop = new AnzeigeFlaeche(); // Hauptfenster erzeugen
    GameOfLife game = new GameOfLife(32, 32,Konstruktionen.GLEITER);
    ViewGame viewGame1;

    public AnzeigeFlaeche(){
        desk = new JDesktopPane(); // neue DesktopPane
        desk.setDesktopManager (new DefaultDesktopManager()); // mit neuem Manager
        setContentPane (desk); // als neue ContentPane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960,1080 ); setLocation(0, 0);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);Ok

        viewGame1 = new ViewGame(desktop, game);
        addChild (viewGame1, 10, 10);
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
        StartGameWindow sgw=new StartGameWindow(desk);      //Creates a Stargame Window
        desktop.addChild (sgw, 10, 10); // Ein Kindfenster einfuegen
        sgw.setSize(200,150);
    }

}

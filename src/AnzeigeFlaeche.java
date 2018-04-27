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

    public AnzeigeFlaeche(){
        desk = new JDesktopPane(); // neue DesktopPane
        desk.setDesktopManager (new DefaultDesktopManager()); // mit neuem Manager
        setContentPane (desk); // als neue ContentPane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080 ); setLocation(250, 340);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setTitle("Game Of Life");
        setVisible(true);
    }
    public void addChild (JInternalFrame child, int x, int y) { // Hinzufuegen
        child.setLocation (x, y); // Ort und
        child.setSize (1280, 720); // Groesse setzen
        child.setDefaultCloseOperation (JInternalFrame.DISPOSE_ON_CLOSE); // Schiessoperation
        desk.add (child); // Kindfenster einfuegenchild.setVisible (true); // und sichtbar machen
    }
    public static void main (String[] args) {
        StartGameWindow sgw=new StartGameWindow(desk);      //Creates a Stargame Window
        desktop.addChild (sgw, 10, 10); // Ein Kindfenster einfuegen
        sgw.setSize(200,150);
    }

}

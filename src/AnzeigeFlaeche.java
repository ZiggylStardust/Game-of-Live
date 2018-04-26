import javax.swing.*;

public class AnzeigeFlaeche extends JFrame{
    private JDesktopPane desk;
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
        GameOfLife temp = new GameOfLife(32, 32);
        AnzeigeFlaeche desktop = new AnzeigeFlaeche(); // Hauptfenster erzeugen
        ViewGame viewGame1 = new ViewGame(desktop);
        BoardView boardView1 = new BoardView(temp, viewGame1);
        viewGame1.add(boardView1);
        desktop.addChild (viewGame1, 10, 10); // Ein Kindfenster einfuegen
    }

}

import javax.swing.*;

public class AnzeigeFlaeche extends JFrame{
    private JDesktopPane desk;
    static AnzeigeFlaeche desktop = new AnzeigeFlaeche(); // Hauptfenster erzeugen

    public AnzeigeFlaeche(){
        desk = new JDesktopPane(); // neue DesktopPane
        desk.setDesktopManager (new DefaultDesktopManager()); // mit neuem Manager
        setContentPane (desk); // als neue ContentPane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600); setLocation(250, 340);
        setTitle("Game Of Life");
        setVisible(true);
    }
    public void addChild (JInternalFrame child, int x, int y) { // Hinzufuegen
        child.setLocation (x, y); // Ort und
        child.setSize (200, 150); // Groesse setzen
        child.setDefaultCloseOperation (JInternalFrame.DISPOSE_ON_CLOSE); // Schiessoperation
        desk.add (child); // Kindfenster einfuegenchild.setVisible (true); // und sichtbar machen
    }
    public static void main (String[] args) {
        GameOfLife temp = new GameOfLife(5, 7);
        AnzeigeFlaeche desktop = new AnzeigeFlaeche(); // Hauptfenster erzeugen
        ViewGame viewGame1 = new ViewGame(desktop);
        BoardView boardView1 = new BoardView(temp);
        viewGame1.add(boardView1);
        desktop.addChild (viewGame1, 10, 10); // Ein Kindfenster einfuegen
    }

}

import javax.swing.*;

/**
 * Class to represent the desktop
 *
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 01/05/18
 */
public class DesktopWindow extends JFrame {
    static JDesktopPane desk;
    static DesktopWindow desktop = new DesktopWindow(); // Hauptfenster erzeugen
    int SCALEFACTOR = 15; //Windowsize is based on Gamesize*SCALEFACTOR

    public DesktopWindow() {
        desk = new JDesktopPane(); // neue DesktopPane
        desk.setDesktopManager(new DefaultDesktopManager()); // mit neuem Manager
        setContentPane(desk); // als neue ContentPane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 1080);
        setLocation(0, 0);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        GameOfLife game = new GameOfLife(64, 32, Construction.GLIDER);  //game created with Gleiter Figure
        ViewGame viewGame = new ViewGame(this, game);
        addChild(viewGame, 50, 50, game);

        setTitle("Game Of Life");
        setVisible(true);
    }

    public void addChild(JInternalFrame child, int x, int y, GameOfLife game) { // Hinzufuegen

        child.setLocation(x, y); // Ort und
        child.setSize(game.getLength() * SCALEFACTOR, game.getHeight() * SCALEFACTOR); // Sets Window size based on Game size
        child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE); // Schiessoperation
        desk.add(child); // Kindfenster einfuegenchild.setVisible (true); // und sichtbar machen
    }

    public static void main(String[] args) {
    }

}

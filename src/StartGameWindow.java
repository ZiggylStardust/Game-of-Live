import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameWindow extends JInternalFrame implements ActionListener{
    private JDesktopPane desk;
    private JTextField xSize=new JTextField("Enter x Size (only Integer)");
    private JTextField ySize=new JTextField("Enter y Size (only Integer)");
    private JButton startGame=new JButton("Start");
    private static int x=0;
    private static int y=0;

    public StartGameWindow(JDesktopPane desk){
        this.desk=desk;
        this.setLayout(new FlowLayout());
        xSize.addActionListener(this);
        ySize.addActionListener(this);
        startGame.addActionListener(this);
        this.setSize(200,100);

        this.add(xSize);
        this.add(ySize);
        this.add(startGame);
        setVisible(true);
        this.setDefaultCloseOperation (JInternalFrame.DISPOSE_ON_CLOSE);

    }
    public void addChild (JInternalFrame child, int x, int y) { // Hinzufuegen
        child.setLocation (x, y); // Ort und
        child.setSize (1280, 720); // Groesse setzen
        child.setDefaultCloseOperation (JInternalFrame.DISPOSE_ON_CLOSE); // Schiessoperation
        desk.add (child); // Kindfenster einfuegenchild.setVisible (true); // und sichtbar machen
    }
    public  static void start(){
        GameOfLife game = new GameOfLife(x, y,Konstruktionen.GLEITER);
        ViewGame viewGame1 = new ViewGame(AnzeigeFlaeche.desktop, game);
        BoardView boardView1 = new BoardView(game, viewGame1);
        viewGame1.add(boardView1);
        game.addObserver(boardView1);
        AnzeigeFlaeche.desktop.addChild (viewGame1, 10, 10); // Ein Kindfenster einfuegen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toString()){
            case "xSize":{
                    if(isNumeric(xSize.getText())){
                        x=Integer.parseInt(xSize.getText());
                    }
                    break;
                }
            case "ySize":{
                if(isNumeric(ySize.getText())){
                    x=Integer.parseInt(ySize.getText());
                }
            }
            case "Start":{
                if(isNumeric(xSize.getText())) {
                    x = Integer.parseInt(xSize.getText());
                }
                if(isNumeric(ySize.getText())){
                    y=Integer.parseInt(ySize.getText());
                }
                if(y>0&&x>0){
                    start();
                }
                break;
            }
        }

    }
    public static boolean isNumeric(String str)
    {
        if(str==null) return false;
        try
        {
            Integer i = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Window that gives option to enter the size of a new game
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 27/04/18
 */
public class StartGameWindow extends JInternalFrame implements ActionListener{
    private JDesktopPane desk;
    private JTextField xSize=new JTextField("Enter length (only Integer)"); //Textfield to enter the height and length
    private JTextField ySize=new JTextField("Enter height (only Integer)");
    private JButton startGame=new JButton("Start");                     //startbutton
    private static int x=0;
    private static int y=0;

    /**
     * Construktor, adds Listeners to Button
     * @param desk      //refernce to JDesktopPane
     */
    public StartGameWindow(JDesktopPane desk){
        this.desk=desk;
        this.setLayout(new FlowLayout());
        startGame.addActionListener(this);
        this.setSize(200,100);

        this.add(xSize);
        this.add(ySize);
        this.add(startGame);
        setVisible(true);
        this.setDefaultCloseOperation (JInternalFrame.DISPOSE_ON_CLOSE);

    }

    /**
     * start methode, called to create a new game
     */
    public  static void start(){
        GameOfLife game = new GameOfLife(x, y,Konstruktionen.GLEITER);  //game created with Gleiter Figure
        ViewGame viewGame1 = new ViewGame(AnzeigeFlaeche.desktop, game);
        BoardView boardView1 = new BoardView(game, viewGame1);
        viewGame1.setBoardView(boardView1);
        viewGame1.add(boardView1);
        game.addObserver(boardView1);
        AnzeigeFlaeche.desktop.addChild (viewGame1, 10, 10); // Ein Kindfenster einfuegen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toString()){

            case "Start":{
                if(isNumeric(xSize.getText())) {            //checks if Text was an integer
                    x = Integer.parseInt(xSize.getText());  //saves the entered text as int
                }
                if(isNumeric(ySize.getText())){
                    y=Integer.parseInt(ySize.getText());
                }
                if(y>0&&x>0){                               //checks if positive numbers where entered
                    start();
                }
                break;
            }
        }

    }
    public static boolean isNumeric(String str)                 //checks if a String is an Integer
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Class to show the Gamefield
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 27/04/18
 */
public class BoardView extends JPanel implements Observer{
    private GameOfLife model;       //Refernces to the game and view
    private ViewGame viewGame;
    private JButton boardElements[][];  //Array of buttons, represents the gamefields

    boolean flipX=false;               //false is normal, 1 is flipped
    boolean flipY=false;

    /**
     *
     * @param model The gamemodel
     * @param viewGame The window
     */
    public BoardView(GameOfLife model, ViewGame viewGame) {
        this.model = model;
        this.viewGame=viewGame;
        this.setLayout(new GridLayout(model.getHeight(), model.getLength()));       //Layout of Buttons
        initializeBoard();
        updateBoard();
    }

    private void initializeBoard() {
        boardElements = new JButton[model.getLength()][model.getHeight()];
        for(int i = 0; i < model.getHeight(); i++) {
            for(int j = 0; j < model.getLength(); j++) {
                final int x=j;
                final int y=i;
                boardElements[j][i] = new JButton();
                add(boardElements[j][i]);
                boardElements[j][i].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {       //Painting hy passing over buttons
                        if(model.isPaint){                           //If isPaint is true
                            model.reanimateCell(x,y);                               //reanimate passed over cell
                            boardElements[x][y].setBackground(viewGame.getAlive());

                        }
                    }
            });
                boardElements[j][i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(model.isSet){         //setting cell to alive
                            model.reanimateCell(x,y);
                            boardElements[x][y].setBackground(viewGame.getAlive());
                        }
                        if(viewGame.isFigure){                  //Setting Figure to the clicked cell
                            model.addFigure(x,y,viewGame.getFigure());
                        }
                    }
                } );
            }
        }
    }

    /**
     * Update board methode, checks the array of cells and recolors the buttons accordingly
     */
    private void updateBoard() {
            for (int i = 0; i < model.getHeight(); i++) {
                for (int j = 0; j < model.getLength(); j++) {
                    boolean modelElement = model.fields[j][i];
                    int x = flipX ? model.getLength() - 1 - j : j;
                    int y = flipX ? model.getLength() - 1 - i : i;

                    if (modelElement) {
                        boardElements[x][y].setBackground(viewGame.getAlive());
                    } else {
                        boardElements[x][y].setBackground(viewGame.getDead());
                    }
                }



        }
        System.out.println();
        }

        public void remapButtons() {
            for (int i = 0; i < (model.getHeight()); i++) {
                for (int j = 0; j < (model.getLength()); j++) {
                remove(boardElements[j][i]);
                }

        }for (int i = 0; i < (model.getHeight()); i++) {
                for (int j = 0; j < (model.getLength()); j++) {
                    int x = flipX ? model.getLength() - 1 - j : j;
                    int y = flipX ? model.getLength() - 1 - i : i;

                    add(boardElements[x][y]);
                }

            }

        }

    @Override
    public void update(Observable o, Object arg) {
        if(o == model) {
            updateBoard();
        }
    }

}

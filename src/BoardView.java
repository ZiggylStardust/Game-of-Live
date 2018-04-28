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
public class BoardView extends JPanel implements Observer {
    private GameOfLife model;       //Refernces to the game and view
    private ViewGame viewGame;
    private JButton boardElements[][];  //Array of buttons, represents the gamefields

    boolean flipX = false;               //false is normal, true is flipped
    boolean flipY = false;

    /**
     * @param model    The gamemodel
     * @param viewGame The window
     */
    public BoardView(GameOfLife model, ViewGame viewGame) {
        this.model = model;
        this.viewGame = viewGame;
        model.addObserver(this);

        this.setLayout(new GridLayout(model.getHeight(), model.getLength()));       //Layout of Buttons
        initializeBoard();
        updateBoard();
    }

    private void initializeBoard() {
        boardElements = new JButton[model.getLength()][model.getHeight()];
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getLength(); j++) {
                final int x = j;
                final int y = i;
                boardElements[j][i] = new JButton();
                add(boardElements[j][i]);
                boardElements[j][i].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {       //Painting hy passing over buttons
                        if (model.isPaint) {                           //If isPaint is true
                           toggleCell(x, y);                          //reanimate passed over cell
                        }
                    }
                });
                boardElements[j][i].addActionListener(e -> {
                    if (model.isSet) {//setting cell to alive
                        toggleCell(x, y);
                    }
                    if (viewGame.isFigure) {                  //Setting Figure to the clicked cell
                        model.addFigure(getCellX(x), getCellY(y), viewGame.getFigure());
                    }
                });
            }
        }
    }

    /**
     * Update board methode, checks the array of cells and recolors the buttons accordingly
     */
    private void updateBoard() {
        //System.out.println(flipX);
        for (int y = 0; y < model.getHeight(); y++) {
            for (int x = 0; x < model.getLength(); x++) {
                boolean modelElement = getCell(x, y);

                if (modelElement) {
                    boardElements[x][y].setBackground(viewGame.getAlive());
                } else {
                    boardElements[x][y].setBackground(viewGame.getDead());
                }
            }
        }
    }


    private void toggleCell(int x, int y) {
        setCell(x, y, !getCell(x, y));
    }
    private void setCell(int x, int y, boolean cell) {
         model.setField(cell, getCellX(x), getCellY(y));
    }
    private boolean getCell(int x, int y) {
        return model.getField(getCellX(x), (getCellY(y)));
    }
    private int getCellX(int x) {
        return flipY ? model.getLength() - 1 - x : x;
    }
    private int getCellY(int y) {
        return flipX ? model.getHeight() - 1 - y : y;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == model) {
            updateBoard();
        }
    }

}

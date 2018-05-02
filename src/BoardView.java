import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Class to show the Gamefield
 *
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 01/05/18
 */
public class BoardView extends JPanel implements Observer {
    private GameOfLife model;       //Refernces to the game and view
    private ViewGame viewGame;
    private JButton boardElements[][];  //Array of buttons, represents the gamefields

    private boolean flipX = false;               //false is normal, true is flipped
    private boolean flipY = false;
    boolean rotate = false;
    public ActualButtonPosition[][] rotPosition;
    public JButton[][] rotBoardElemnts;         //Array of JButtons, rotated 90°
    private GridLayout grid;
    private GridLayout rotGrid;


    /**
     * @param model    The gamemodel
     * @param viewGame The window
     */
    public BoardView(GameOfLife model, ViewGame viewGame) {
        this.model = model;
        grid = new GridLayout(model.getHeight(), model.getLength());
        rotGrid = new GridLayout(model.getLength(), model.getHeight());
        this.viewGame = viewGame;
        rotPosition = new ActualButtonPosition[model.getHeight()][model.getLength()];
        rotBoardElemnts = new JButton[model.getHeight()][model.getLength()];
        boardElements = new JButton[model.getLength()][model.getHeight()];
        model.addObserver(this);
        this.setLayout(grid);       //Layout of Buttons
        initializeBoard();
    }

    private void initializeBoard() {
        for (int y = 0; y < model.getHeight(); y++) {
            for (int x = 0; x < model.getLength(); x++) {
                final int xPos = x;
                final int yPos = y;
                rotPosition[model.getHeight() - 1 - y][x] = new ActualButtonPosition(x, y);
                boardElements[x][y] = new JButton();
                rotBoardElemnts[model.getHeight() - 1 - y][x] = boardElements[x][y];

                add(boardElements[x][y]);
                boardElements[x][y].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {       //Painting hy passing over buttons
                        if (model.isPaint) {                           //If isPaint is true
                            setCell(xPos, yPos, true);                          //reanimate passed over cell
                        }
                    }
                });
                boardElements[x][y].addActionListener(e -> {

                    if (model.isSet) {//setting cell to alive
                        toggleCell(xPos, yPos);
                    }
                    if (viewGame.isFigure) {                  //Setting Figure to the clicked cell
                        model.addFigure(getCellX(xPos), getCellY(yPos), viewGame.getFigure());
                    }
                    if (!model.isSet && !viewGame.isFigure) {           //Cells can be set alive always, but only killed in Set Mode
                        setCell(xPos, yPos, true);
                    }
                });
            }
        }

    }

    /**
     * Update board methode, checks the array of cells and recolors the buttons accordingly
     */
    private void updateBoard() {
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

    /**
     * Update methode to be called while rotated, uses rotated Boardelemnts and
     * rotPosition to get actual Position in field
     */
    private void rotateUpdate() {
        for (int y = 0; y < model.getLength(); y++) {
            for (int x = 0; x < model.getHeight(); x++) {
                boolean modelElement = getCell(rotPosition[x][y].x, rotPosition[x][y].y);
                if (modelElement) {
                    rotBoardElemnts[x][y].setBackground(viewGame.getAlive());
                } else {
                    rotBoardElemnts[x][y].setBackground(viewGame.getDead());
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

        return flipX ? model.getLength() - 1 - x : x;

    }

    private int getCellY(int y) {

        return flipY ? model.getHeight() - 1 - y : y;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    /**
     * Methode to rotate, sets the flips the roatation anf flipX varaible
     * replaces the Layout with the rotated Layout
     * removes buttons and adds the rotated ones
     */
    public void rotate() {
        rotate = !rotate;
        if (rotate) {


            for (int y = 0; y < model.getHeight(); y++) {
                for (int x = 0; x < model.getLength(); x++) {
                    remove(boardElements[x][y]);
                }
            }
            this.setLayout(rotGrid);
            this.validate();
            for (int y = 0; y < model.getLength(); y++) {
                for (int x = 0; x < model.getHeight(); x++) {
                    add(rotBoardElemnts[x][y]);
                }
            }

            rotateUpdate();
            setVisible(false);
            setVisible(true);

        } else {
            for (int y = 0; y < model.getLength(); y++) {
                for (int x = 0; x < model.getHeight(); x++) {
                    remove(rotBoardElemnts[x][y]);
                }
            }

            for (int y = 0; y < model.getHeight(); y++) {
                for (int x = 0; x < model.getLength(); x++) {
                    add(boardElements[x][y]);
                }
            }
            this.setLayout(grid);
            this.validate();
            updateBoard();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == model) {
            if (!rotate) {
                updateBoard();
            } else {
                rotateUpdate();
            }
        }
    }
}

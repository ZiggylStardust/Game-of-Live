import javax.swing.*;
import java.awt.*;
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

    private boolean flipX = false;               //false is normal, true is flipped
    private boolean flipY = false;
    boolean rotate=false;
    public int rotLength;
    public int rotHeight;
    GridLayout grid;
    /**
     * @param model    The gamemodel
     * @param viewGame The window
     */
    public BoardView(GameOfLife model, ViewGame viewGame) {
        this.model = model;
        grid=new GridLayout(model.getHeight(),model.getLength());
        this.viewGame = viewGame;
        model.addObserver(this);
        rotHeight =model.getLength();
        rotLength =model.getHeight();
        this.setLayout(grid);       //Layout of Buttons
        initializeBoard();
        updateBoard();
    }

    private void initializeBoard() {
        boardElements = new JButton[model.getLength()][model.getHeight()];
        for (int y = 0; y < model.getHeight(); y++) {
            for (int x = 0; x < model.getLength(); x++) {
                final int xPos = x;
                final int yPos = y;
                boardElements[x][y] = new JButton();
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
                    if(!model.isSet&&!viewGame.isFigure){           //Cells can be set alive always, but only killed in Set Mode
                        setCell(xPos,yPos,true);
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
    private void rotateUpdate(){
        for (int y = 0; y < rotHeight; y++) {
            for (int x = 0; x < rotLength; x++) {
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
        if(!rotate) {
            return model.getField(getCellX(x), (getCellY(y)));
        }
        else {
            return model.getField(getCellX(y), (getCellY(x)));

        }
    }
    private int getCellX(int x) {

        return flipY ? model.getLength() - 1 - x : x;
    }
    private int getCellY(int y) {
        return flipX ? model.getHeight() - 1 - y : y;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
        updateBoard();
    }

    public boolean isFlipX() {
        return flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
        updateBoard();
    }


    public void initRotate(){

        if(!rotate){
            initializeBoard();
        }
        else {

            grid=new GridLayout(rotHeight, rotLength);
            this.setLayout(grid);
            this.validate();

            boardElements = new JButton[rotLength][rotHeight];
            for (int y = 0; y < rotHeight; y++) {
                for (int x = 0; x < rotLength; x++) {
                    final int xPos = x;
                    final int yPos = y;
                    boardElements[x][y] = new JButton();
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
                        if(!model.isSet&&!viewGame.isFigure){           //Cells can be set alive always, but only killed in Set Mode
                            setCell(xPos,yPos,true);
                        }
                    });
                }
            }

            rotateUpdate();

        }
    }
    @Override

    public void update(Observable o, Object arg) {
        if(o == model) {
            if(!rotate) {
                updateBoard();
            }
            else{
                rotateUpdate();
            }
        }
    }
}

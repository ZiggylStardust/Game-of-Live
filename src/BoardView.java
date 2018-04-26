import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JPanel implements Observer{
    private GameOfLife model;
    ViewGame viewGame;
    private JButton boardElements[][];

    public BoardView(GameOfLife model, ViewGame viewGame) {
        this.model = model;
        this.viewGame=viewGame;
        System.out.println(model.getHeight());
        this.setLayout(new GridLayout(model.getLength(), model.getHeight()));
        initializeBoard();

        updateBoard();
    }

    private void initializeBoard() {
        boardElements = new JButton[model.getLength()][model.getHeight()];
        for(int i = 0; i < model.getLength(); i++) {
            for(int j = 0; j < model.getHeight(); j++) {
                final int x=i;
                final int y=j;
                boardElements[i][j] = new JButton();
                add(boardElements[i][j]);
                boardElements[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if(viewGame.isPaint){
                            model.reanimateCell(x,y);
                        }
                    }
            });
            }
        }
    }

    private void updateBoard() {
        for(int i = 0; i < model.getLength(); i++) {
            for(int j = 0; j < model.getHeight(); j++) {
                if(model.feld[i][j]){
                    boardElements[i][j].setBackground(Color.RED);
                } else {
                    boardElements[i][j].setBackground(Color.GREEN);
                }
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

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JPanel implements Observer {
    private GameOfLife model;
    private JButton boardElements[][];

    public BoardView(GameOfLife model) {
        this.model = model;
        this.setLayout(new GridLayout(model.getLength(), model.getHeight()));
        initializeBoard();

        updateBoard();
    }

    private void initializeBoard() {
        boardElements = new JButton[model.getLength()][model.getHeight()];
        for(int i = 0; i < model.getLength(); i++) {
            for(int j = 0; j < model.getHeight(); j++) {
                boardElements[i][j] = new JButton();
                add(boardElements[i][j]);
            }
        }
    }

    private void updateBoard() {
        for(int i = 0; i < model.getLength(); i++) {
            for(int j = 0; j < model.getHeight(); j++) {
                if(model.feld[i][j]){
                    boardElements[i][j].setText("I");
                } else {
                    boardElements[i][j].setText("-");
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

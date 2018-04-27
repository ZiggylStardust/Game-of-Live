import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JPanel implements Observer{
    private GameOfLife model;
    private ViewGame viewGame;
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
        for(int i = 0; i < model.getHeight(); i++) {
            for(int j = 0; j < model.getLength(); j++) {
                final int x=j;
                final int y=i;
                boardElements[j][i] = new JButton();
                add(boardElements[j][i]);
                boardElements[j][i].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if(viewGame.passBoolean.isPaint){
                            model.reanimateCell(x,y);
                            boardElements[x][y].setBackground(viewGame.getAlive());

                        }
                    }
            });
                boardElements[j][i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(viewGame.passBoolean.isSet){
                            model.reanimateCell(x,y);
                            boardElements[x][y].setBackground(viewGame.getAlive());
                        }
                        if(viewGame.isFigure){
                            model.addFigure(x,y,viewGame.getFigure());
                        }
                    }
                } );
            }
        }
    }

    private void updateBoard() {
        if(!viewGame.flip) {
            for (int i = 0; i < model.getHeight(); i++) {
                for (int j = 0; j < model.getLength(); j++) {
                    if (model.feld[j][i]) {
                        boardElements[j][i].setBackground(viewGame.getAlive());
                    } else {
                        boardElements[j][i].setBackground(viewGame.getDead());
                    }
                }
            }
        }
        else{
            for (int i = 0; i < model.getHeight(); i++) {
                for (int j = 0; j < model.getLength(); j++) {

                    if (model.feld[j][i]) {
                        boardElements[model.getLength()-1-j][i].setBackground(viewGame.getAlive());
                    } else {
                        boardElements[model.getLength()-1-j][i].setBackground(viewGame.getDead());
                    }
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

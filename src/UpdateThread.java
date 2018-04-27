/**
 * Thread class, used to update the game
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 27/04/18
 */
public class UpdateThread extends Thread {
    public int speed=500;      //wait between update in miliseconds
    public GameOfLife game;
    public ViewGame view;
    public UpdateThread(GameOfLife game, ViewGame view){
        this.game=game;
        this.view=view;
    }
    @Override
    public void run() {
        while(view.passBoolean.isRun) {
            game.updateFeld();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

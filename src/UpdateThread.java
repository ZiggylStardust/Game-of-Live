import java.util.Observable;
import java.util.Observer;

/**
 * Thread class, used to update the game
 *
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 27/04/18
 */
public class UpdateThread extends Thread {
    public int speed = 500;      //wait between update in miliseconds
    public GameOfLife game;     //refernce to the game it's connected to

    public UpdateThread(GameOfLife game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (!game.isDone()) {
            if (game.isRun) {
                game.updateFeld();
            }
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

    public int getSpeed() {
        return speed;
    }
}

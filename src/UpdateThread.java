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
        while(view.isRun) {
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

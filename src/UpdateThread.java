public class UpdateThread extends Thread {
    public int speed=500;      //wait between update in miliseconds
    public GameOfLife game;
    public UpdateThread(GameOfLife game){
        this.game=game;
    }
    @Override
    public void run() {
        System.out.println("test");
        while(true) {
            if(game.isRun) {
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
}

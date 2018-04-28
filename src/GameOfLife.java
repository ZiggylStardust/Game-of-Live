import java.util.Observable;

/**
 * Game of Live Main logic class
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 27/04/18
 */
public class GameOfLife extends Observable {
    public boolean isRun=false;         // checks if game is suposed to be paused
    public boolean isPaint =false;
    public boolean isSet=false;
    UpdateThread thread = new UpdateThread(this);
    private boolean[][] fields;        //fields der Zellen, true ist lebende, false ist tote Zelle

    /**
     * Construktor, setzte größe des Feldes
     * @param x         Anzahl Zellen nach rechts
     * @param y         Anzahl Zellen nach unten
     */
    GameOfLife(int x, int y) {
        fields = new boolean[x][y];
    }

    /**
     * Construktor, setzte größe des Feldes und man kann Muster initialisieren
     * @param x         Anzahl Zellen nach rechts
     * @param y         Anzahl Zellen nach unten
     * @param muster    enum, das ein muster wiedergibt, welches Angezeigt wird
     */

    GameOfLife(int x, int y, Konstruktionen muster){
        fields = new boolean[x][y];
        boolean[][] figur=KonstruktionsFeld.getForm(muster);
        if(figur.length< getLength()&&figur[0].length< getHeight()){
            for(y=0;y<figur[0].length;y++){
                for(x=0;x<figur.length;x++){
                    fields[x][y]=figur[x][y];
                }
            }
        }

        thread.start();
    }

    /**
     * Sets the velue of a field to that of teh paramater value
     * @param value     the new value
     * @param x         the x Position
     * @param y         the y position
     */
    public void setField(boolean value, int x, int y) {
        if(fields[x][y] != value) {
            fields[x][y] = value;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * returns the value of a field
     * @param x     the x value of the field
     * @param y     the y value
     * @return      the value of the field
     */
    public boolean getField(int x, int y) {
        return fields[x][y];
    }

    /**
     * overwrites the gamefield with a new gamefield after update
     * @param fields    the updated game field
     */
    private void setFields(boolean[][] fields) {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getLength(); x++) {
                if(this.fields[x][y] != fields[x][y]) {
                    this.fields[x][y] = fields[x][y];
                }
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Update das fields, durchs Array iterieren und auf jedes fields checkSurrounding anwenden, dann in neues Array kopieren
     * und dann das alte mit diesem überschreiben
     */
    public void updateFeld() {

        boolean[][] tempFeld = new boolean[getLength()][getHeight()];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getLength(); x++) {
            tempFeld[x][y]=checkSurrounding(x,y);
            }
        }
        setFields(tempFeld);
    }

    /**
     *
     * @param initial_x         x Position der Zelle (links rechts)
     * @param initial_y         y Position der Zelle (oben unten)
     * @return          Ist die Zelle lebendig ?(true =ja, false =nein)
     */
    public boolean checkSurrounding(int initial_x, int initial_y) {
        boolean cellsLives = false;     //return wert
        int countOfLivingCells = 0;     // Anzahl der lebenden Zellen, die sie umgeben
        for (int y = initial_y - 1; y <= initial_y + 1; y++) {       //über die 8 Zellen itereiren, die die Zelle be x,y umgibt
            for (int x = initial_x - 1; x <= initial_x + 1; x++) {
                int yPos = y;                       //Position der Zelle, die jetzt betrachtet wird (eine der Umgebenden Zellen)
                int xPos = x;
                /**
                 * Überprüft, ob die Zelle auserhalb des Arrays liegt, wenn das Passiert, wird die gegüberliegnde Zelle betrachet
                 */
                if(!(x==initial_x&&y==initial_y)) {
                    if (xPos == -1) {
                        xPos = getLength() - 1;
                    }
                    if (xPos == getLength()) {
                        xPos = 0;
                    }
                    if (yPos == -1) {
                        yPos = getHeight() - 1;
                    }
                    if (yPos == getHeight()) {
                        yPos = 0;
                    }
                    if (fields[xPos][yPos]) {         //Wenn die betrachtete Zelle lebendig ist, wird der Zähler erhöht

                        countOfLivingCells++;
                    }
                }
            }
        }

        /**
         * Implementierung der Regeln des Spiels
         */
        if (!fields[initial_x][initial_y] && countOfLivingCells == 3) {   //bei genau 3 lebenden Zellen wird eine Tote Zelle wiederbelebt
            cellsLives = true;
        }
        if (countOfLivingCells < 2) {       //Bei Unter 2 umgebenden Zellen nstirbt sie
            cellsLives = false;
        }
        if (fields[initial_x][initial_y] && countOfLivingCells >= 2) {        //Bei 2 oder 3 Zellen bleibt sie am Leben
            cellsLives = true;
        }
        if (countOfLivingCells > 3) {                       //Bei über 3 Zellen stirbt sie
            cellsLives = false;
        }
        return cellsLives;
    }

    /**
     *resets the field = kills every cell
     */
    public void resetFeld(){
        setFields(new boolean[getLength()][getHeight()]);
    }
    public int getHeight(){
        return fields[0].length;
    }
    public int getLength(){
        return fields.length;
    }

    /**
     * Places a figure in the game
     * @param initial_x     The x value where the figure begins
     * @param initial_y     The y Value where the figure begins
     * @param figure
     */
    public void addFigure(int initial_x, int initial_y, boolean[][] figure){
        for(int y=0;y<figure[0].length;y++){
            for(int x=0;x<figure.length;x++){
                if(figure[x][y]){
                    int xPos=(x+initial_x)%getLength();     //Modulo used to prevent out of bounds exeptions
                    int yPos=(y+initial_y)%getHeight();
                    setField(true, xPos, yPos);
                    }
                }
            }
        setChanged();
        notifyObservers();
    }

    /**
     * Used to set the speed = wait time for thread
     * @param speed     spped in miliseconds to wait for update
     */
    public void setSpeed(int speed) {
        thread.setSpeed(speed);
    }
}


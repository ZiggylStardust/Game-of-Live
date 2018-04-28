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

    public void setField(boolean value, int x, int y) {
        if(fields[x][y] != value) {
            fields[x][y] = value;
            setChanged();
            notifyObservers();
        }
    }

    public boolean getField(int x, int y) {
        return fields[x][y];
    }

    private void setFields(boolean[][] fields) {
        for (int y = 0; y < getLength(); y++) {
            for (int x = 0; x < getHeight(); x++) {
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
     * @param x         x Position der Zelle (links rechts)
     * @param y         y Position der Zelle (oben unten)
     * @return          Ist die Zelle lebendig ?(true =ja, false =nein)
     */
    public boolean checkSurrounding(int x, int y) {
        boolean cellsLives = false;     //return wert
        int countOfLivingCells = 0;     // Anzahl der lebenden Zellen, die sie umgeben
        for (int i = y - 1; i <= y + 1; i++) {       //über die 8 Zellen itereiren, die die Zelle be x,y umgibt
            for (int k = x - 1; k <= x + 1; k++) {
                int yPos = i;                       //Position der Zelle, die jetzt betrachtet wird (eine der Umgebenden Zellen)
                int xPos = k;
                /**
                 * Überprüft, ob die Zelle auserhalb des Arrays liegt, wenn das Passiert, wird die gegüberliegnde Zelle betrachet
                 */
                if(!(k==x&&i==y)) {
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
        if (!fields[x][y] && countOfLivingCells == 3) {   //bei genau 3 lebenden Zellen wird eine Tote Zelle wiederbelebt
            cellsLives = true;
        }
        if (countOfLivingCells < 2) {       //Bei Unter 2 umgebenden Zellen nstirbt sie
            cellsLives = false;
        }
        if (fields[x][y] && countOfLivingCells >= 2) {        //Bei 2 oder 3 Zellen bleibt sie am Leben
            cellsLives = true;
        }
        if (countOfLivingCells > 3) {                       //Bei über 3 Zellen stirbt sie
            cellsLives = false;
        }
        return cellsLives;
    }

    /**
     * Belebe Zelle an position(x,y)
     * @param x x Position
     * @param y y Position
     */
    public void reanimateCell(int x, int y){
        setField(true, x, y);
    }
    /**
     * Tötet Zelle an position(x,y)
     * @param x x Position
     * @param y y Position
     */
    public void killCell(int x, int y){
        setField(false, x, y);
    }

    /**
     * Setzte fields zurück auf alles Tod, daher alles false, indem man es durch neues Array ersetzt (default boolean ist false)
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

    public void addFigure(int x, int y, boolean[][] figure){
        for(int i=0;i<figure[0].length;i++){
            for(int k=0;k<figure.length;k++){
                if(figure[k][i]){
                    int xPos=(x+k)%getLength();
                    int yPos=(y+i)%getHeight();
                    setField(true, xPos, yPos);
                    }
                }
            }
        setChanged();
        notifyObservers();
    }

    public void setSpeed(int speed) {
        thread.setSpeed(speed);
    }
}


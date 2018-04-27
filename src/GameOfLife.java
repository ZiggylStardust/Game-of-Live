import java.util.Observable;

/**
 * Game of Live Main logic class
 *
 */
public class GameOfLife extends Observable {
    public boolean[][] feld;        //feld der Zellen, true ist lebende, false ist tote Zelle

    /**
     * Construktor, setzte größe des Feldes
     * @param x         Anzahl Zellen nach rechts
     * @param y         Anzahl Zellen nach unten
     */
    GameOfLife(int x, int y) {
        feld = new boolean[x][y];
    }

    /**
     * Construktor, setzte größe des Feldes und man kann Muster initialisieren
     * @param x         Anzahl Zellen nach rechts
     * @param y         Anzahl Zellen nach unten
     * @param muster    enum, das ein muster wiedergibt, welches Angezeigt wird
     */

    GameOfLife(int x, int y, Konstruktionen muster){
        feld = new boolean[x][y];
        boolean[][] figur=KonstruktionsFeld.getForm(muster);
        if(figur.length< getLength()&&figur[0].length< getHeight()){
            for(y=0;y<figur[0].length;y++){
                for(x=0;x<figur.length;x++){
                    feld[x][y]=figur[x][y];
                }
            }
        }
    }

    /**
     * Update das feld, durchs Array iterieren und auf jedes feld checkSurrounding anwenden, dann in neues Array kopieren
     * und dann das alte mit diesem überschreiben
     */
    public void updateFeld() {

        boolean[][] tempFeld = new boolean[getLength()][getHeight()];
        for (int y = 0; y < getLength(); y++) {
            for (int x = 0; x < getHeight(); x++) {
            tempFeld[x][y]=checkSurrounding(x,y);
            }
        }
        for (int y = 0; y < getLength(); y++) {
            for (int x = 0; x < getHeight(); x++) {
                feld[x][y]=tempFeld[x][y];
            }
        }
        setChanged();
        notifyObservers();

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
                    if (feld[xPos][yPos]) {         //Wenn die betrachtete Zelle lebendig ist, wird der Zähler erhöht

                        countOfLivingCells++;
                    }
                }
            }
        }

        /**
         * Implementierung der Regeln des Spiels
         */
        if (!feld[x][y] && countOfLivingCells == 3) {   //bei genau 3 lebenden Zellen wird eine Tote Zelle wiederbelebt
            cellsLives = true;
        }
        if (countOfLivingCells < 2) {       //Bei Unter 2 umgebenden Zellen nstirbt sie
            cellsLives = false;
        }
        if (feld[x][y] && countOfLivingCells >= 2) {        //Bei 2 oder 3 Zellen bleibt sie am Leben
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
        feld[x][y]=true;

    }

    /**
     * Setzte feld zurück auf alles Tod, daher alles false, indem man es durch neues Array ersetzt (default boolean ist false)
     */
    public void resetFeld(){
        feld =new boolean[getLength()][getHeight()];
    }
    public int getHeight(){
        return feld[0].length;
    }
    public int getLength(){
        return feld.length;
    }

    public void addFigure(int x, int y, boolean[][] figure){
        for(int i=0;i<figure[0].length;i++){
            for(int k=0;k<figure.length;k++){
                if(figure[k][i]){
                    int xPos=(x+k)%getLength();
                    int yPos=(y+i)%getHeight();
                    feld[xPos][yPos]=true;
                    }
                }
            }
        setChanged();
        notifyObservers();
    }


    }


/**
 * Game of Live Main logic class
 *
 */
public class GameOfLife {
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
        if(figur.length< feld.length&&figur[0].length< feld[0].length){
            for(x=0;x<figur.length;x++){
                for(y=0;y<figur[0].length;y++){
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

        boolean[][] tempFeld = new boolean[feld.length][feld[0].length];
        for (int x = 0; x < feld.length; x++) {
            for (int y = 0; y < feld[0].length; y++) {
            tempFeld[x][y]=checkSurrounding(x,y);
            }
        }
        for (int x = 0; x < feld.length; x++) {
            for (int y = 0; y < feld[0].length; y++) {
                feld[x][y]=tempFeld[x][y];
            }
        }
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
        for (int i = x - 1; i < x + 1; i++) {       //über die 8 Zellen itereiren, die die Zelle be x,y umgibt
            for (int k = y - 1; k < y + 1; k++) {
                int xPos = i;                       //Position der Zelle, die jetzt betrachtet wird (eine der Umgebenden Zellen)
                int yPos = k;
                /**
                 * Überprüft, ob die Zelle auserhalb des Arrays liegt, wenn das Passiert, wird die gegüberliegnde Zelle betrachet
                 */
                if(xPos!=x&&yPos!=y) {
                    if (xPos == -1) {
                        xPos = feld.length - 1;
                    }
                    if (xPos == feld.length) {
                        xPos = 0;
                    }
                    if (yPos == -1) {
                        yPos = feld[0].length - 1;
                    }
                    if (yPos == feld[0].length) {
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
        feld =new boolean[feld.length][feld[0].length];
    }
    public int getHight(){
        return feld[0].length;
    }
    public int getLenght(){
        return feld.length;
    }

}

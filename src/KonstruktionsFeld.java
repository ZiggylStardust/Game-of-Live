/**
 * Speichert die Muster als boolean array, und gibt sie aus
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 27/04/18
 */
public class KonstruktionsFeld {
    static private boolean[][] gleiter=    {{false,true, false},   //Gleiter als boolean Array
                                    {false, false,true},
                                    {true,true,true}};
    static private boolean[][] fPentomino=
                    {{false, true,true},    //f-Pentomino, chaotische entwicklung
                    {true, true,false},
                    {false,true,false}};
    static private boolean[][] blinker={{true},{true},{true}}; //Blinker, ändert seine ausrichtung
    static private boolean[][] bipole={{true, true, false,false},{true, true, false,false},
                                        {false, false,true,true},{false, false,true,true}};

    /**
     *
     * @param muster    enum des musters, welches gegeben werden soll
     * @return          das muster
     */
    public static boolean[][] getForm(Konstruktionen muster){
        switch (muster){
            case GLEITER: return gleiter;
            case F_PENTOMINO: return fPentomino;
            case BLINKER: return blinker;
            case BIPLOE:return bipole;

        }
    return new boolean[1][1];   //default return, leeres Array, eine tote zelle
    }
}

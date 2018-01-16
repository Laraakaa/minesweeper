package tk.routeconnect.minesweeper;

import tk.routeconnect.minesweeper.benutzereingabe.BenutzerEingabe;
import tk.routeconnect.minesweeper.benutzereingabe.EingabeFehler;
import tk.routeconnect.minesweeper.spielfeld.Spielfeld;

/**
 * Der InputProcessor handhabt Benutzereingaben
 */
class InputProcessor {

    private Spielfeld spielfeld;

    InputProcessor(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    /**
     * Die Methode processInput nimmt Benuteranweisungen entgegen und verarbeitet sie
     * @param eingabe Hier ist der Input des Benutzer: T (Test) oder M (Markieren) sowie nachfolgend X und Y Koordinaten angeben
     * @throws EingabeFehler Wird ein Eingabewert eingegeben welcher nicht verarbeitet werden kann, wird ein EingabeFehler geworfen
     */
    void processInput(BenutzerEingabe eingabe) throws EingabeFehler {
        switch (eingabe.getTyp()) {
            case T:
                spielfeld.test(eingabe.getX(), eingabe.getY());
                break;
            case M:

                break;
            default:
                throw new EingabeFehler("Diese Eingabe kann ich leider nicht verarbeiten.");
        }
    }
}

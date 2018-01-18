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
     * @return true if the game has ended, false otherwise
     */
    boolean processInput(BenutzerEingabe eingabe) throws EingabeFehler {
        switch (eingabe.getTyp()) {
            case T:
                return spielfeld.test(eingabe.getX(), eingabe.getY());
            case M:
                spielfeld.mark(eingabe.getX(), eingabe.getY());
                break;
            default:
                throw new EingabeFehler("Diese Eingabe kann ich leider nicht verarbeiten.");
        }
        return false;
    }
}

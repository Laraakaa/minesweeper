package tk.routeconnect.minesweeper;

import tk.routeconnect.minesweeper.benutzereingabe.BenutzerEingabe;
import tk.routeconnect.minesweeper.benutzereingabe.EingabeFehler;
import tk.routeconnect.minesweeper.spielfeld.Spielfeld;

class InputProcessor {

    private Spielfeld spielfeld;

    InputProcessor(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

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

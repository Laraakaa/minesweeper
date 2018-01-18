package tk.routeconnect.minesweeper;

import tk.routeconnect.minesweeper.benutzereingabe.BenutzerEingabe;
import tk.routeconnect.minesweeper.benutzereingabe.EingabeFehler;
import tk.routeconnect.minesweeper.benutzereingabe.EingabeTyp;
import tk.routeconnect.minesweeper.spielfeld.Spielfeld;

import java.util.Scanner;

/**
 * Representiert eine Schnittstelle mit dem Nutzer.
 * Diese Schnittstelle kann später gekapselt werden (UI)
 */
class UserInterface {

    /**
     * Der Scanner, über den die Eingabe (STDIN) gelesen wird
     */
    private Scanner scanner;

    /**
     * Der InputProcessor, der die Eingaben verarbeitet
     */
    private InputProcessor processor;

    /**
     * Das Spielfeld, welches die Spiellogik verwaltet
     */
    private Spielfeld spielfeld = new Spielfeld();

    /**
     * Ob die Applikation beenden soll
     */
    private boolean isCancelled = false;

    /**
     * Erstellt ein neues UserInterface, initialisiert ein InputProcessor und den Scanner.
     */
    UserInterface() {
        processor = new InputProcessor(spielfeld);
        scanner = new Scanner(System.in);
    }

    /**
     * Liest die nächste Eingabe ein.
     * Delegiert sie zur Verarbeitung zu dem InputProcessor.
     */
    void read() {
        if (scanner.hasNext()) {
            String input = scanner.nextLine();

            try {
                BenutzerEingabe eingabe = BenutzerEingabe.parse(input);

                if (eingabe.getTyp().equals(EingabeTyp.EXIT)) {
                    isCancelled = true;
                    return;
                }

                if (processor.processInput(eingabe)) {
                    isCancelled = true;
                }
            } catch (EingabeFehler e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void revealAll() {
        spielfeld.revealAll();
    }

    /**
     * @return ob die Applikation beenden soll
     */
    boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Zeigt das aktuelle Spielfeld formatiert an.
     * Proxy für die Methode {@link Spielfeld#display() spielfeld.display()}
     */
    void display() {
        spielfeld.display();
    }

}

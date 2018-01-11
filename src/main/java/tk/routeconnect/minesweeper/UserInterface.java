package tk.routeconnect.minesweeper;

import tk.routeconnect.minesweeper.benutzereingabe.BenutzerEingabe;
import tk.routeconnect.minesweeper.benutzereingabe.EingabeFehler;

import java.util.Scanner;

/**
 * Das UserInterface ist für die Interaktion mit dem User zuständig
 */
class UserInterface {
    private Scanner scanner;
    private InputProcessor processor = new InputProcessor();

    UserInterface() {
        scanner = new Scanner(System.in);
    }

    /**
     * Die read Methode liest eine Benutereingabe ein und validiert diese, dies so lang, bis eine gültige Eingabe vorliegt
     */
    void read() {
        if (scanner.hasNext()) {
            String input = scanner.nextLine();

            try {
                BenutzerEingabe eingabe = BenutzerEingabe.parse(input);
                processor.processInput(eingabe);
            } catch (EingabeFehler e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

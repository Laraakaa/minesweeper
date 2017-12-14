package tk.routeconnect.minesweeper;

import tk.routeconnect.minesweeper.benutzereingabe.BenutzerEingabe;
import tk.routeconnect.minesweeper.benutzereingabe.EingabeFehler;

import java.util.Scanner;

class UserInterface {
    private Scanner scanner;
    private InputProcessor processor = new InputProcessor();

    UserInterface() {
        scanner = new Scanner(System.in);
    }

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

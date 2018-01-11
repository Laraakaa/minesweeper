package tk.routeconnect.minesweeper;

/**
 * Der GameLoop, startet die Hauptschlaufe (GameLoop)
 */
class GameLoop {

    private UserInterface userInterface;

    GameLoop() {
        userInterface = new UserInterface();
    }

    /**
     * Die run() Methode, führt den Spieler in die Spielregeln ein und startet die erste Spielschlaufe
     */
    void run() {
        System.out.println("----------------------------------");
        System.out.println("      Welcome to MineSweeper      ");
        System.out.println("----------------------------------");
        System.out.println("");
        System.out.println("Sie können nun zwei Arten von Aktionen ausführen:");
        System.out.println("  - Ein Feld markieren:");
        System.out.println("  Nutzen Sie den Befehl 'M [X] [Y]' um ein Feld zu markieren");
        System.out.println("  - Ein Feld testen / aufdecken:");
        System.out.println("  Nutzen Sie den Befehl 'T [X] [Y]' um ein Feld aufzudecken");

        while(true) {
            userInterface.read();
        }
    }
}

package tk.routeconnect.minesweeper;

class GameLoop {

    private UserInterface userInterface;

    GameLoop() {
        userInterface = new UserInterface();
    }

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

        while(!userInterface.isCancelled()) {
            userInterface.display();
            userInterface.read();
        }

        System.out.println("Die Applikation wurde beendet.");
    }
}

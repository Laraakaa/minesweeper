package tk.routeconnect.minesweeper;

/**
 * Die Main Klasse
 */
public class Main {

    /**
     * Der Programmeinstiegspunkt - die main Mathode
     * @param args mögliche Startparameter (zzt. nicht unterstützt)
     */
    public static void main(String[] args) {
        GameLoop gameLoop = new GameLoop();

        gameLoop.run();
    }
}

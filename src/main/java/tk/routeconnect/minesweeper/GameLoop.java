package tk.routeconnect.minesweeper;

class GameLoop {

    private UserInterface userInterface;

    GameLoop() {
        userInterface = new UserInterface();
    }

    void run() {
        while(true) {
            userInterface.read();
        }
    }
}

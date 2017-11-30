package tk.routeconnect.minesweeper;

import java.util.Scanner;

class UserInterface {
    Scanner scanner;

    UserInterface() {
        scanner = new Scanner(System.in);
    }

    void read() {
        if (scanner.hasNext()) {
            String input = scanner.nextLine();
        }
    }

}

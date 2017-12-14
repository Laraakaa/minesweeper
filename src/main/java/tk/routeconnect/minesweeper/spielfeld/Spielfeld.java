package tk.routeconnect.minesweeper.spielfeld;

import java.util.ArrayList;

public class Spielfeld {

    ArrayList<ArrayList<Zelle>> zellen = new ArrayList<>(new ArrayList<>());
    int size = 9;

    public Spielfeld() {
        for (int i = 0; i < size; i++) {
            ArrayList<Zelle> zeile = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                zeile.add(new Zelle());
            }
            zellen.add(zeile);
        }
    }
}

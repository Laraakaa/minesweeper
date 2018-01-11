package tk.routeconnect.minesweeper.spielfeld;

import java.util.ArrayList;
import java.util.Random;

public class Spielfeld {

    ArrayList<ArrayList<Zelle>> zellen = new ArrayList<>(new ArrayList<>());
    int size = 9;
    int bombs = 10;
    Random rdm = new Random();

    public Spielfeld() {
        for (int i = 0; i < size; i++) {
            ArrayList<Zelle> zeile = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                zeile.add(new Zelle());
            }
            zellen.add(zeile);
        }

        for (int i = 0; i < bombs; i++) {
            int x = rdm.nextInt(9);
            int y = rdm.nextInt(9);
            Zelle zelle = getZelle(x, y);

            if (zelle.isBomb()) {
                i--;
                continue;
            }

            zelle.setBomb(true);
        }
        ausgeben();
    }

    private Zelle getZelle(int x, int y) throws IndexOutOfBoundsException {
        return zellen.get(x).get(y);
    }

    public void test(int x, int y) {
        Zelle selected = getZelle(x, y);
        System.out.println(selected);
    }

    public void ausgeben() {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(getZelle(i, j).display());
            }
            System.out.print("\n");
        }
    }
}

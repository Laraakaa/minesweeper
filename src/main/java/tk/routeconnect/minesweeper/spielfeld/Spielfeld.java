package tk.routeconnect.minesweeper.spielfeld;

import java.util.ArrayList;
import java.util.Random;

/**
 * Das Spielfeld verwaltet die verschiedenen Zellen, die auf ihm liegen.
 */
public class Spielfeld {

    /**
     * Zwei-dimensionales Array aller Zellen
     */
    private ArrayList<ArrayList<Zelle>> zellen = new ArrayList<>(new ArrayList<>());

    /**
     * Die Grösse des Spielfelds (quadratisch)
     */
    private int size = 9;

    /**
     * Die Anzahl der Bomben
     */
    private int bombs = 10;

    /**
     * Der Randomgenerator der zum Bestimmen der Bombenpositionen benutzt wird
     */
    private Random rdm = new Random();

    /**
     * Erstellt ein neues Spielfeld mit leeren Zellen, füllt diese anschliessend mit Bomben.
     */
    public Spielfeld() {
        for (int i = 0; i < size; i++) {
            ArrayList<Zelle> zeile = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                zeile.add(new Zelle(this, i, j));
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

        // display(true);
    }

    /**
     * Retourniert eine Zelle an gewissen Koordinaten.
     * @param x X-Koordinate (0-Index)
     * @param y Y-Koordinate (0-Index)
     * @return die Zelle an dieser Position
     * @throws IndexOutOfBoundsException wenn die Koordinate ausserhalb des Spielfelds liegt
     */
    private Zelle getZelle(int x, int y) throws IndexOutOfBoundsException {
        return zellen.get(x).get(y);
    }

    private boolean isWon() {
        return zellen.stream().anyMatch(zeile -> zeile.stream().noneMatch(zelle -> (zelle.isBomb() && zelle.isMarked()) || (!zelle.isBomb() && zelle.isRevealed())));
    }

    /**
     * Retourniert eine Zelle relativ zu einer anderen.
     * @param start Die Ursprungszelle
     * @param xModifier Die Veränderung der X-Koordinate (positiv oder negativ)
     * @param yModifier Die Veränderung der Y-Koordinate (positiv oder negativ)
     * @return die Zelle an der neuen Position
     * @throws IndexOutOfBoundsException wenn das neue Ziel ausserhalb des Spielfelds liegt
     */
    private Zelle getRelative(Zelle start, int xModifier, int yModifier) throws IndexOutOfBoundsException {
        int newX = start.getX() + xModifier;
        int newY = start.getY() + yModifier;

        if (newX < 0 || newX >= size || newY < 0 || newY >= size) {
            return null;
        }
        return getZelle(newX, newY);
    }

    /**
     * Retourniert ein Array aus allen umliegenden Zellen einer Zelle.
     * @param field Die Ursprungszelle
     * @return Alle (bis zu 8) umliegenden Zellen.
     */
    ArrayList<Zelle> getSurroundingFields(Zelle field) {
        ArrayList<Zelle> fields = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Zelle relative = getRelative(field, i, j);
                if (relative != null) {
                    fields.add(relative);
                }
            }
        }

        return fields;
    }

    /**
     * Testet eine Zelle auf eine Bombe, deckt sie um.
     * @param x X-Koordinate der Zelle (0-Index)
     * @param y Y-Koordinate der Zelle (0-Index)
     * @return true if the game should end, false oterhwise
     * @throws IndexOutOfBoundsException wenn die Zelle auserhalb des Spielfelds liegt
     */
    public boolean test(int x, int y) throws IndexOutOfBoundsException {
        Zelle selected = getZelle(x, y);

        if (selected.isRevealed()) {
            System.out.println("Dieses Feld ist schon aufgedeckt!");
            return false;
        }

        if (selected.isBomb()) {
            System.out.println("Du bist in eine Bombe gelaufen. Du hast leider verloren :(");
            return true;
        }

        selected.reveal();

        if (!isWon()) {
            System.out.println("Du hast das Spielfeld freigeräumt und damit gewonnen!");
            return true;
        }
        return false;
    }

    public void mark(int x, int y) throws IndexOutOfBoundsException {
        Zelle selected = getZelle(x, y);

        if (selected.isRevealed()) {
            System.out.println("Dieses Feld wurde bereits aufgedeckt und kann nicht mehr markiert werden.");
            return;
        }

        selected.toggleMark();
    }

    public void display() {
        display(false);
    }

    /**
     * Zeigt das Spielfeld formatiert in der Konsole an.
     */
    public void display(boolean debug) {
        for(int i = -1; i < size; i++) {
            if (i == -1) {
                System.out.print("   ");
            } else {
                System.out.print(" " + i + " ");
            }
            for (int j = 0; j < size; j++) {
                if (i == -1) {
                    System.out.print(" " + j + " ");
                } else {
                    System.out.print(" " + getZelle(j, i).display(debug) + " ");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Reveals all cells
     */
    public void revealAll() {
        zellen.forEach(zeile -> zeile.forEach(Zelle::reveal));
    }
}

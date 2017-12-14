package tk.routeconnect.minesweeper.benutzereingabe;

public class BenutzerEingabe {

    public static BenutzerEingabe parse(String input) throws EingabeFehler {

        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new EingabeFehler("Das Format muss wie folgt angegeben werden: [TYPE] [X] [Y], z.B. 'T 1 1'");
        }

        String typeStr = parts[0];
        String xStr = parts[1];
        String yStr = parts[2];

        EingabeTyp typ;
        try {
            typ = EingabeTyp.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            throw new EingabeFehler("Dieser Typ ist invalid. Bitte wähle entweder 't' zum aufdecken, oder 'm' zum markieren.");
        }

        int x;
        try {
            x = Integer.valueOf(xStr);
        } catch (NumberFormatException e) {
            throw new EingabeFehler("Die X-Koordinate muss eine Zahl sein.");
        }

        int y;
        try {
            y = Integer.valueOf(xStr);
        } catch (NumberFormatException e) {
            throw new EingabeFehler("Die Y-Koordinate muss eine Zahl sein.");
        }

        return new BenutzerEingabe(typ, x, y);
    }

    private EingabeTyp typ;
    private int x;
    private int y;

    private BenutzerEingabe(EingabeTyp typ, int x, int y) {
        this.typ = typ;
        this.x = x;
        this.y = y;
    }

    public EingabeTyp getTyp() {
        return typ;
    }

    public void setTyp(EingabeTyp typ) {
        this.typ = typ;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

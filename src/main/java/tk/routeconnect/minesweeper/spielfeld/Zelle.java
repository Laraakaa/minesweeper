package tk.routeconnect.minesweeper.spielfeld;

class Zelle {

    private boolean isBomb = false;

    void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    boolean isBomb() {
        return isBomb;
    }

    String display() {
        if (isBomb) {
            return "*";
        }
        return "-";
    }

}

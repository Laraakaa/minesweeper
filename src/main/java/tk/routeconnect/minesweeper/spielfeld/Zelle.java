package tk.routeconnect.minesweeper.spielfeld;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Zelle {
    private Spielfeld assignedMatchfield;

    private int x;
    private int y;

    private boolean isBomb = false;
    private boolean isRevealed = false;
    private boolean isMarked = false;

    Zelle(Spielfeld assignedMatchfield, int x, int y) {
        this.assignedMatchfield = assignedMatchfield;
        this.x = x;
        this.y = y;
    }

    void toggleMark() {
        isMarked = !isMarked;
    }

    private int getNumberOfSurroundingBombs() {
        List<Zelle> cells = assignedMatchfield.getSurroundingFields(this);
        cells = cells.stream().filter(Zelle::isBomb).collect(Collectors.toList());
        return cells.size();
    }

    String display(boolean debug) {
        if (isMarked) {
            return "M";
        }
        if (!isRevealed && !debug) {
            return "-";
        }
        if (isBomb) {
            return "*";
        }
        int numberOfBombs = getNumberOfSurroundingBombs();
        if (numberOfBombs == 0) {
            return " ";
        }
        return String.valueOf(numberOfBombs);
    }

    void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    boolean isBomb() {
        return isBomb;
    }

    void reveal() {
        if (isRevealed) {
            return;
        }
        this.isRevealed = true;
        this.isMarked = false;

        ArrayList<Zelle> surroundingFields = assignedMatchfield.getSurroundingFields(this);
        if (surroundingFields.stream().noneMatch(Zelle::isBomb)) {
            for (Zelle surroundingField : surroundingFields) {
                if (!surroundingField.isBomb()) {
                    surroundingField.reveal();
                }
            }
        }
    }

    boolean isRevealed() {
        return isRevealed;
    }

    boolean isMarked() {
        return isMarked;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}

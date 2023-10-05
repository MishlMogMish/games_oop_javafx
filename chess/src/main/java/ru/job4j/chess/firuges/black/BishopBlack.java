package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static java.lang.Math.abs;

public class BishopBlack implements Figure {
    private final Cell pos;

    public BishopBlack(final Cell ps) {
        pos = ps;
    }

    @Override
    public Cell position() {
        return pos;
    }

    @Override
    public Cell[] way(Cell dest) {
        if (!isDiagonal(pos, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not way by diagonal from %s to %s", pos, dest)
            );
        }
        int size = abs(dest.getX() - pos.getX());
        Cell[] steps = new Cell[size];
        int deltaX = dest.getX() > pos.getX() ? 1 : -1;
        int deltaY = dest.getY() > pos.getY() ? 1 : -1;
        int x = pos.getX();
        int y = pos.getY();
        for (int i = 0; i < size; i++) {
            x += deltaX;
            y += deltaY;
            steps[i] = Cell.findBy(x, y);
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        return (abs(source.getX() - dest.getX())
                == abs(source.getY() - dest.getY()));
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}

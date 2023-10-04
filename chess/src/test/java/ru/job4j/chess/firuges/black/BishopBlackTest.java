package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class BishopBlackTest {

    @Test
    void whenCreateFigureThenPositionIsCreatedPosition() {
        Cell cell = Cell.F8;
        BishopBlack bishop = new BishopBlack(cell);
        Cell result = bishop.position();
        assertThat(cell.getX()).isEqualTo(result.getX());
        assertThat(cell.getY()).isEqualTo(result.getY());

    }

    @Test
    void whenCopyThenPositionIsCopiedPosition() {
        Cell cell = Cell.F8;
        BishopBlack bishop = new BishopBlack(cell);
        Cell destCell = Cell.A3;
        Figure result = bishop.copy(destCell);
        Cell expected = destCell;
        assertThat(expected.getX()).isEqualTo(result.position().getX());
        assertThat(expected.getY()).isEqualTo(result.position().getY());
    }

    @Test
    void whenWayFromC1ToG5ThenD2E3F4G5() {
        Cell cell = Cell.C1;
        BishopBlack bishop = new BishopBlack(cell);
        Cell destCell = Cell.G5;
        Cell[] result = bishop.way(destCell);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        for (int i = 0; i < result.length; i++) {
            assertThat(expected[i].getX()).isEqualTo(result[i].getX());
            assertThat(expected[i].getY()).isEqualTo(result[i].getY());
        }
    }

    @Test
    void whenNotInDiagonalThenIsDiagonalFalse() {
        Cell cell = Cell.D6;
        Cell destCell = Cell.E6;
        BishopBlack bishop = new BishopBlack(cell);
        boolean result = bishop.isDiagonal(cell, destCell);
        assertThat(result).isFalse();
    }

    @Test
    void whenNotInDiagonalThenImpossibleMoveException() {
        Cell cell = Cell.D6;
        Cell destCell = Cell.E6;
        BishopBlack bishop = new BishopBlack(cell);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishop.way(destCell);
                });
        assertThat(exception.getMessage())
                .isEqualTo("Could not way by diagonal from "
                        + cell.name() + " to " + destCell.name());
    }
}
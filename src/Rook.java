import java.util.ArrayList;

public class Rook extends Piece {
    public boolean rockadable;
    public Rook(int color, int row, int column) {
        super(Piece.Rook, color, row, column, 50);
        rockadable = true;
    }

    @Override
    public void move(ArrayList<String> moves, Piece[][] matrix) {
        Board board = Board.getInstance();
        String newMove, currentPosition;
        int x, y, takeColor;

        currentPosition = new String(Character.toString((char) ('a' + column)) + ((char) ('1' + row)));
        if (color == Piece.White)
            takeColor = Piece.Black;
        else takeColor = Piece.White;

        // possible moves to left
        for (x = column + 1; x < 8; x++) {
            newMove = new String(currentPosition + ((char) ('a' + x)) + ((char) ('1' + row)));

            if ((matrix[row][x] == null || matrix[row][x].color == takeColor)
                    && !Board.checkChess(Board.generateNextBoard(matrix, newMove), color)) {
                moves.add(newMove);
                if (matrix[row][x] != null)
                    break;
            }
            else {
                break;
            }
        }

        // possible moves to right
        for (x = column - 1; x >= 0; x--) {
            newMove = new String(currentPosition + ((char) ('a' + x)) + ((char) ('1' + row)));

            if ((matrix[row][x] == null || matrix[row][x].color == takeColor)
                    && !Board.checkChess(Board.generateNextBoard(matrix, newMove), color)) {
                moves.add(newMove);
                if (matrix[row][x] != null)
                    break;
            }
            else {
                break;
            }
        }

        // possible moves upwards
        for (y = row + 1; y < 8; y++) {
            newMove = new String(currentPosition + ((char) ('a' + column)) + ((char) ('1' + y)));

            if ((matrix[y][column] == null || matrix[y][column].color == takeColor)
                    && !Board.checkChess(Board.generateNextBoard(matrix, newMove), color)) {
                moves.add(newMove);
                if (matrix[y][column] != null)
                    break;
            }
            else {
                break;
            }
        }

        // possible moves downwards
        for (y = row - 1; y >= 0; y--) {
            newMove = new String(currentPosition + ((char) ('a' + column)) + ((char) ('1' + y)));

            if ((matrix[y][column] == null || matrix[y][column].color == takeColor)
                    && !Board.checkChess(Board.generateNextBoard(matrix, newMove), color)) {
                moves.add(newMove);
                if (matrix[y][column] != null)
                    break;
            }
            else {
                break;
            }
        }
    }

    @Override
    public Piece makeCopy() {
        Rook newRook = new Rook(color, row, column);
        newRook.rockadable = rockadable;
        return newRook;
    }
}

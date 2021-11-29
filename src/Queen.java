import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(int color, int row, int column) {
        super(Piece.Queen, color, row, column, 90);
    }

    @Override
    public void move(ArrayList<String> moves, Piece[][] matrix) {
        String newMove, currentPosition;
        int x, y, takeColor, i;

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

        // possible moves down-left
        for (i = 1; i < 8; i++) {
            x = column - i; y = row - i;
            if (x < 0 || y < 0)
                break;
            newMove = new String(currentPosition + ((char) ('a' + x)) + ((char) ('1' + y)));
            if ((matrix[y][x] == null || matrix[y][x].color == takeColor)
                    && !Board.checkChess(Board.generateNextBoard(matrix, newMove), color)) {
                System.out.println(1);
                moves.add(newMove);
                if (matrix[y][x] != null)
                    break;
            }
            else {
                break;
            }
        }

        // possible moves down-right
        for (i = 1; i < 8; i++) {
            x = column - i; y = row + i;
            if (x < 0 || y >= 8)
                break;
            newMove = new String(currentPosition + ((char) ('a' + x)) + ((char) ('1' + y)));
            if ((matrix[y][x] == null || matrix[y][x].color == takeColor)
                    && !Board.checkChess(Board.generateNextBoard(matrix, newMove), color)) {
                System.out.println(2);
                moves.add(newMove);
                if (matrix[y][x] != null)
                    break;
            }
            else {
                break;
            }
        }

        // possible moves up-left
        for (i = 1; i < 8; i++) {
            x = column + i; y = row - i;
            if (x >= 8 || y < 0)
                break;
            newMove = new String(currentPosition + ((char) ('a' + x)) + ((char) ('1' + y)));
            if ((matrix[y][x] == null || matrix[y][x].color == takeColor)
                    && !Board.checkChess(Board.generateNextBoard(matrix, newMove), color)) {
                System.out.println(3);
                moves.add(newMove);
                if (matrix[y][x] != null)
                    break;
            }
            else {
                break;
            }
        }

        // possible moves up-right
        for (i = 1; i < 8; i++) {
            x = column + i; y = row + i;
            if (x >= 8 || y >= 8)
                break;
            newMove = new String(currentPosition + ((char) ('a' + x)) + ((char) ('1' + y)));
            if ((matrix[y][x] == null || matrix[y][x].color == takeColor)
                    && !Board.checkChess(Board.generateNextBoard(matrix, newMove), color)) {
                System.out.println(4);
                moves.add(newMove);
                if (matrix[y][x] != null)
                    break;
            }
            else {
                break;
            }
        }
    }

    @Override
    public Piece makeCopy() {
        return new Queen(color, row, column);
    }
}

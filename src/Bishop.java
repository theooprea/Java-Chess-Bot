import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(int color, int row, int column) {
        super(Piece.Bishop, color, row, column, 30);
    }

    @Override
    public void move(ArrayList<String> moves, Piece[][] matrix) {
        String newMove, currentPosition;
        int x, y, takeColor, i;

        currentPosition = new String(Character.toString((char) ('a' + column)) + ((char) ('1' + row)));
        if (color == Piece.White)
            takeColor = Piece.Black;
        else takeColor = Piece.White;

        // possible moves down-left
        for (i = 1; i < 8; i++) {
            x = column - i;
            y = row - i;
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
            x = column - i;
            y = row + i;
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
            x = column + i;
            y = row - i;
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
            x = column + i;
            y = row + i;
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
        return new Bishop(color, row, column);
    }
}

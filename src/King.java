import java.util.ArrayList;

public class King extends Piece {
    public boolean rockadable;
    public King(int color, int row, int column) {
        super(Piece.King, color, row, column, 0);
        rockadable = true;
    }

    @Override
    public void move(ArrayList<String> moves, Piece[][] matrix) {
        // Rocade
        if (color == Piece.White) {
            if (matrix[0][1] == null && matrix[0][2] == null && matrix[0][3] == null && rockadable &&
                    matrix[0][0] != null && matrix[0][0].type == Piece.Rook &&
                    matrix[0][0].color == Piece.White && ((Rook)matrix[0][0]).rockadable) {
                if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix, "a1d1"), "e1c1"), Piece.White)
                    && !Board.checkChess(Board.generateNextBoard(matrix, "e1d1"), Piece.White)
                    && !Board.checkChess(Board.generateNextBoard(matrix, "e1c1"), Piece.White)) {
                    moves.add("e1c1");
                }
            }
            if (matrix[0][5] == null && matrix[0][6] == null && rockadable && matrix[0][7] != null &&
                    matrix[0][7].type == Piece.Rook && matrix[0][7].color == Piece.White &&
                    ((Rook)matrix[0][7]).rockadable) {
                if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix, "h1f1"), "e1g1"), Piece.White)
                    && !Board.checkChess(Board.generateNextBoard(matrix, "e1f1"), Piece.White)
                    && !Board.checkChess(Board.generateNextBoard(matrix, "e1g1"), Piece.White)) {
                    moves.add("e1g1");
                }
            }
        }
        else {
            if (matrix[7][1] == null && matrix[7][2] == null && matrix[7][3] == null && rockadable &&
                    matrix[7][0] != null && matrix[7][0].type == Piece.Rook &&
                    matrix[7][0].color == Piece.Black && ((Rook)matrix[7][0]).rockadable) {
                if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix, "a8d8"), "e8c8"), Piece.Black)
                    && !Board.checkChess(Board.generateNextBoard(matrix, "e8d8"), Piece.Black)
                    && !Board.checkChess(Board.generateNextBoard(matrix, "e8c8"), Piece.Black)) {
                    moves.add("e8c8");
                }
            }
            if (matrix[7][5] == null && matrix[7][6] == null && rockadable && matrix[7][7] != null &&
                    matrix[7][7].type == Piece.Rook && matrix[7][7].color == Piece.Black &&
                    ((Rook)matrix[7][7]).rockadable) {
                if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix, "h8f8"), "e8g8"), Piece.Black)
                    && !Board.checkChess(Board.generateNextBoard(matrix, "e8f8"), Piece.Black)
                    && !Board.checkChess(Board.generateNextBoard(matrix, "e8g8"), Piece.Black)) {
                    moves.add("e8g8");
                }
            }
        }

        if (row + 1 >= 0 && row + 1 <= 7 && column + 1 >= 0 && column + 1 <= 7) {
            if (matrix[row + 1][column + 1] == null || matrix[row + 1][column + 1].color != color) {
                char first = (char)('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix, Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('2' + row)), color)) {
                    moves.add(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('2' + row));
                }
            }
        }
        if (row >= 0 && row <= 7 && column + 1 >= 0 && column + 1 <= 7) {
            if (matrix[row][column + 1] == null || matrix[row][column + 1].color != color) {
                char first = (char)('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix, Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('1' + row)), color)) {
                    moves.add(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('1' + row));
                }
            }
        }
        if (row - 1 >= 0 && row - 1<= 7 && column + 1 >= 0 && column + 1 <= 7) {
            if (matrix[row - 1][column + 1] == null || matrix[row - 1][column + 1].color != color) {
                char first = (char)('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix, Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('0' + row)), color)) {
                    moves.add(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('0' + row));
                }
            }
        }

        if (row + 1 >= 0 && row + 1 <= 7 && column - 1 >= 0 && column - 1 <= 7) {
            if (matrix[row + 1][column - 1] == null || matrix[row + 1][column - 1].color != color) {
                char first = (char)('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix, Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('2' + row)), color)) {
                    moves.add(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('2' + row));
                }
            }
        }
        if (row >= 0 && row <= 7 && column - 1 >= 0 && column - 1 <= 7) {
            if (matrix[row][column - 1] == null || matrix[row][column - 1].color != color) {
                char first = (char)('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix, Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('1' + row)), color)) {
                    moves.add(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('1' + row));
                }
            }
        }
        if (row - 1 >= 0 && row - 1<= 7 && column - 1 >= 0 && column - 1 <= 7) {
            if (matrix[row - 1][column - 1] == null || matrix[row - 1][column - 1].color != color) {
                char first = (char)('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix, Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('0' + row)), color)) {
                    moves.add(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('0' + row));
                }
            }
        }

        if (row + 1 >= 0 && row + 1 <= 7 && column >= 0 && column <= 7) {
            if (matrix[row + 1][column] == null || matrix[row + 1][column].color != color) {
                char first = (char)('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix, Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('2' + row)), color)) {
                    moves.add(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('2' + row));
                }
            }
        }

        if (row - 1 >= 0 && row - 1 <= 7 && column >= 0 && column <= 7) {
            if (matrix[row - 1][column] == null || matrix[row - 1][column].color != color) {
                char first = (char)('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix, Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('0' + row)), color)) {
                    moves.add(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('0' + row));
                }
            }
        }
    }

    @Override
    public Piece makeCopy() {
        King newKing = new King(color, row, column);
        newKing.rockadable = rockadable;
        return newKing;
    }
}

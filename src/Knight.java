import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int color, int row, int column) {
        super(Piece.Knight, color, row, column, 30);
    }

    @Override
    public void move(ArrayList<String> moves, Piece[][] matrix) {
        Board board = Board.getInstance();

        // tratez cele 4 miscari de-a lungul axei orizontale (--| si |--)
        int c, r; // auxliare pentru coloana si rand
        String move; // auxiliar pentru mutare
        // stanga jos
        c = column - 2;
        r = row - 1;
        if (c >= 0 && c <= 7 && r >= 0 && r <= 7) { // destinatia se afla pe tabla
            if (matrix[r][c] == null || matrix[r][c].color != color) {
                move = new String(Character.toString((char)('a' + column)) +
                    (char)('1' + row) + (char)('a' + c) + (char)('1' + r));
                if (!Board.checkChess(Board.generateNextBoard(matrix, move), color)) {
                    moves.add(move);
                }
            }
        }
        // stanga sus
        c = column - 2;
        r = row + 1;
        if (c >= 0 && c <= 7 && r >= 0 && r <= 7) { // destinatia se afla pe tabla
            if (matrix[r][c] == null || matrix[r][c].color != color) {
                move = new String(Character.toString((char)('a' + column)) +
                    (char)('1' + row) + (char)('a' + c) + (char)('1' + r));
                if (!Board.checkChess(Board.generateNextBoard(matrix, move), color)) {
                    moves.add(move);
                }
            }
        }
        // dreapta jos
        c = column + 2;
        r = row - 1;
        if (c >= 0 && c <= 7 && r >= 0 && r <= 7) { // destinatia se afla pe tabla
            if (matrix[r][c] == null || matrix[r][c].color != color) {
                move = new String(Character.toString((char)('a' + column)) +
                    (char)('1' + row) + (char)('a' + c) + (char)('1' + r));
                if (!Board.checkChess(Board.generateNextBoard(matrix, move), color)) {
                    moves.add(move);
                }
            }
        }
        // dreapta sus
        c = column + 2;
        r = row + 1;
        if (c >= 0 && c <= 7 && r >= 0 && r <= 7) { // destinatia se afla pe tabla
            if (matrix[r][c] == null || matrix[r][c].color != color) {
                move = new String(Character.toString((char)('a' + column)) +
                    (char)('1' + row) + (char)('a' + c) + (char)('1' + r));
                if (!Board.checkChess(Board.generateNextBoard(matrix, move), color)) {
                    moves.add(move);
                }
            }
        }
        // tratez cele patru miscari pe verticala
        // jos stanga
        c = column - 1;
        r = row - 2;
        if (c >= 0 && c <= 7 && r >= 0 && r <= 7) { // destinatia se afla pe tabla
            if (matrix[r][c] == null || matrix[r][c].color != color) {
                move = new String(Character.toString((char)('a' + column)) +
                    (char)('1' + row) + (char)('a' + c) + (char)('1' + r));
                if (!Board.checkChess(Board.generateNextBoard(matrix, move), color)) {
                    moves.add(move);
                }
            }
        }
        // sus stanga
        c = column - 1;
        r = row + 2;
        if (c >= 0 && c <= 7 && r >= 0 && r <= 7) { // destinatia se afla pe tabla
            if (matrix[r][c] == null || matrix[r][c].color != color) {
                move = new String(Character.toString((char)('a' + column)) +
                    (char)('1' + row) + (char)('a' + c) + (char)('1' + r));
                if (!Board.checkChess(Board.generateNextBoard(matrix, move), color)) {
                    moves.add(move);
                }
            }
        }
        // jos dreapta
        c = column + 1;
        r = row - 2;
        if (c >= 0 && c <= 7 && r >= 0 && r <= 7) { // destinatia se afla pe tabla
            if (matrix[r][c] == null || matrix[r][c].color != color) {
                move = new String(Character.toString((char)('a' + column)) +
                    (char)('1' + row) + (char)('a' + c) + (char)('1' + r));
                if (!Board.checkChess(Board.generateNextBoard(matrix, move), color)) {
                    moves.add(move);
                }
            }
        }
        // dreapta sus
        c = column + 1;
        r = row + 2;
        if (c >= 0 && c <= 7 && r >= 0 && r <= 7) { // destinatia se afla pe tabla
            if (matrix[r][c] == null || matrix[r][c].color != color) {
                move = new String(Character.toString((char)('a' + column)) +
                    (char)('1' + row) + (char)('a' + c) + (char)('1' + r));
                if (!Board.checkChess(Board.generateNextBoard(matrix, move), color)) {
                    moves.add(move);
                }
            }
        }
    }

    @Override
    public Piece makeCopy() {
        return new Knight(color, row, column);
    }
}

import java.util.ArrayList;

public class Pawn extends Piece {
    public int enPassant;

    public Pawn(int color, int row, int column) {
        super(Piece.Pawn, color, row, column, 10);
        enPassant = 0;
    }

    @Override
    public void move(ArrayList<String> moves, Piece[][] matrix) {
        // Black Pawns
        if (color == Piece.Black) {
            // Check if we can "take" an opponent piece.
            if (column == 0) {
                // Classic move for taking an opponent's piece
                if (matrix[row - 1][1] != null && matrix[row - 1][1].color == Piece.White) {
                    if (!Board.checkChess(Board.generateNextBoard(matrix,
                        new String("a" + (char)('1' + row) + (char)'b' + (char)('0' + row))), Piece.Black)) {
                        if (row == 1) {
                            moves.add(new String("a" + (char)('1' + row) + (char)'b' + (char)('0' + row) + "q"));
                            moves.add(new String("a" + (char)('1' + row) + (char)'b' + (char)('0' + row) + "r"));
                            moves.add(new String("a" + (char)('1' + row) + (char)'b' + (char)('0' + row) + "n"));
                            moves.add(new String("a" + (char)('1' + row) + (char)'b' + (char)('0' + row) + "b"));
                        }
                        else {
                            moves.add(new String("a" + (char)('1' + row) + (char)'b' + (char)('0' + row)));
                        }
                    }
                }
                // En passant move
                if (matrix[3][0] == this && matrix[3][1] != null && matrix[3][1].type == Piece.Pawn) {
                    if (matrix[3][1].color == Piece.White && ((Pawn) matrix[3][1]).enPassantEligible()) {
                        if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix, new String("a4b4")), new String("b4b3")), Piece.Black)) {
                            moves.add(new String("a4b3"));
                        }
                    }
                }
            }
            else if (column == 7) {
                // Classic move for taking an opponent's piece
                if (matrix[row - 1][6] != null && matrix[row - 1][6].color == Piece.White) {
                    if (!Board.checkChess(Board.generateNextBoard(matrix,
                        new String("h" + (char)('1' + row) + (char)'g' + (char)('0' + row))), Piece.Black)) {
                        if (row == 1) {
                            moves.add(new String("h" + (char)('1' + row) + (char)'g' + (char)('0' + row) + "q"));
                            moves.add(new String("h" + (char)('1' + row) + (char)'g' + (char)('0' + row) + "r"));
                            moves.add(new String("h" + (char)('1' + row) + (char)'g' + (char)('0' + row) + "n"));
                            moves.add(new String("h" + (char)('1' + row) + (char)'g' + (char)('0' + row) + "b"));
                        }
                        else {
                            moves.add(new String("h" + (char)('1' + row) + (char)'g' + (char)('0' + row)));
                        }
                    }
                }
                // En passant move
                if (matrix[3][7] == this && matrix[3][6] != null && matrix[3][6].type == Piece.Pawn) {
                    if (matrix[3][6].color == Piece.White && ((Pawn) matrix[3][6]).enPassantEligible()) {
                        if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix, new String("h4g4")), new String("g4g3")), Piece.Black)) {
                            moves.add(new String("h4g3"));
                        }
                    }
                }
            }
            else {
                // Classic move for taking an opponent's piece
                if (matrix[row - 1][column - 1] != null && matrix[row - 1][column - 1].color == Piece.White) {
                    char first = (char) ('a' + column);
                    if (!Board.checkChess(Board.generateNextBoard(matrix,
                        new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('0' + row))), Piece.Black)) {
                        if (row == 1) {
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('0' + row) + "q"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('0' + row) + "r"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('0' + row) + "n"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('0' + row) + "b"));
                        }
                        else {
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('0' + row)));
                        }
                    }
                }
                if (matrix[row - 1][column + 1] != null && matrix[row - 1][column + 1].color == Piece.White) {
                    char first = (char) ('a' + column);
                    if (!Board.checkChess(Board.generateNextBoard(matrix,
                        new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('0' + row))), Piece.Black)) {
                        if (row == 1) {
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('0' + row) + "q"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('0' + row) + "r"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('0' + row) + "n"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('0' + row) + "b"));
                        }
                        else {
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('0' + row)));
                        }
                    }
                }
                // En passant move
                if (matrix[3][column] == this && matrix[3][column - 1] != null
                    && matrix[3][column - 1].type == Piece.Pawn) {
                    if (matrix[3][column - 1].color == Piece.White && ((Pawn) matrix[3][column - 1]).enPassantEligible()) {
                        char first = (char) ('a' + column);
                        if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix,
                            new String(Character.toString(first) + '4' + (char)('a' + column - 1) + "4")),
                            new String(Character.toString((char)('a' + column - 1)) + '4' + (char)('a' + column - 1) + "3")), Piece.Black)) {
                            moves.add(new String(Character.toString(first) + '4' + (char)('a' + column - 1) + "3"));
                        }
                    }
                }
                if (matrix[3][column] == this && matrix[3][column + 1] != null
                    && matrix[3][column + 1].type == Piece.Pawn) {
                    if (matrix[3][column + 1].color == Piece.White && ((Pawn) matrix[3][column + 1]).enPassantEligible()) {
                        char first = (char) ('a' + column);
                        if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix,
                            new String(Character.toString(first) + '4' + (char)('a' + column + 1) + "4")),
                            new String(Character.toString((char)('a' + column + 1)) + '4' + (char)('a' + column + 1) + "3")), Piece.Black)) {
                            moves.add(new String(Character.toString(first) + '4' + (char)('a' + column + 1) + "3"));
                        }
                    }
                }
            }
            // If we can't take any pieces, we try to advance with 1 or two positions.
            if (row == 6) {
                if (matrix[5][column] == null) {
                    if (matrix[4][column] == null) {
                        char first = (char) ('a' + column);
                        if (!Board.checkChess(Board.generateNextBoard(matrix,
                            new String(Character.toString(first) + (char)'7' + (char)('a' + column) + (char)'5')), Piece.Black)) {
                            moves.add(new String(Character.toString(first) + (char)'7' + (char)('a' + column) + (char)'5'));
                        }
                    }
                    else {
                        char first = (char) ('a' + column);
                        if (!Board.checkChess(Board.generateNextBoard(matrix,
                            new String(Character.toString(first) + (char)'7' + (char)('a' + column) + (char)'6')), Piece.Black)) {
                            moves.add(new String(Character.toString(first) + (char)'7' + (char)('a' + column) + (char)'6'));
                        }
                    }
                }
            }
            if (matrix[row - 1][column] == null) {
                char first = (char) ('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix,
                        new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('0' + row))), Piece.Black)) {
                    if (row == 1) {
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('0' + row) + "q"));
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('0' + row) + "r"));
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('0' + row) + "n"));
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('0' + row) + "b"));
                    }
                    else {
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('0' + row)));
                    }
                } 
            }
        }
        // White Pawns
        else {
            // Check if we can take an opponent piece.
            if (column == 0) {
                // Classic move for taking an opponent's piece
                if (matrix[row + 1][1] != null && matrix[row + 1][1].color == Piece.Black) {
                    if (!Board.checkChess(Board.generateNextBoard(matrix,
                        new String("a" + (char) ('1' + row) + (char)'b' + (char) ('2' + row))), Piece.White)) {
                        if (row == 6) {
                            moves.add(new String("a" + (char) ('1' + row) + (char)'b' + (char) ('2' + row) + "q"));
                            moves.add(new String("a" + (char) ('1' + row) + (char)'b' + (char) ('2' + row) + "r"));
                            moves.add(new String("a" + (char) ('1' + row) + (char)'b' + (char) ('2' + row) + "n"));
                            moves.add(new String("a" + (char) ('1' + row) + (char)'b' + (char) ('2' + row) + "b"));
                        }
                        else {
                            moves.add(new String("a" + (char) ('1' + row) + (char)'b' + (char) ('2' + row)));
                        }
                    }
                }
                // En passant move
                if (matrix[4][0] == this && matrix[4][1] != null && matrix[4][1].type == Piece.Pawn) {
                    if (matrix[4][1].color == Piece.White && ((Pawn) matrix[4][1]).enPassantEligible()) {
                        if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix, new String("a5b5")),
                            new String("b5b6")), Piece.White)) {
                            moves.add(new String("a5b6"));
                        }
                    }
                }
            }
            else if (column == 7) {
                // Classic move for taking an opponent's piece
                if (matrix[row + 1][6] != null && matrix[row + 1][6].color == Piece.Black) {
                    if (!Board.checkChess(Board.generateNextBoard(matrix,
                        new String("h" + (char)('1' + row) + 'g' + (char)('2' + row))), Piece.White)) {
                        if (row == 6) {
                            moves.add(new String("h" + (char)('1' + row) + 'g' + (char)('2' + row) + "q"));
                            moves.add(new String("h" + (char)('1' + row) + 'g' + (char)('2' + row) + "r"));
                            moves.add(new String("h" + (char)('1' + row) + 'g' + (char)('2' + row) + "n"));
                            moves.add(new String("h" + (char)('1' + row) + 'g' + (char)('2' + row) + "b"));
                        }
                        else {
                            moves.add(new String("h" + (char)('1' + row) + 'g' + (char)('2' + row)));
                        }
                    }
                }
                // En passant move
                if (matrix[4][7] == this && matrix[4][6] != null && matrix[4][6].type == Piece.Pawn) {
                    if (matrix[4][6].color == Piece.White && ((Pawn) matrix[4][6]).enPassantEligible()) {
                        if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix, new String("h5g5")),
                            new String("g5g6")), Piece.White)) {
                            moves.add(new String("h5g6"));
                        }
                    }
                }
            }
            else {
                // Classic move for taking an opponent's piece
                if (matrix[row + 1][column - 1] != null && matrix[row + 1][column - 1].color == Piece.Black) {
                    char first = (char) ('a' + column);
                    if (!Board.checkChess(Board.generateNextBoard(matrix,
                        new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('2' + row))), Piece.White)) {
                        if (row == 6) {
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('2' + row) + "q"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('2' + row) + "r"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('2' + row) + "n"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('2' + row) + "b"));
                        }
                        else {
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column - 1) + (char)('2' + row)));
                        }
                    }
                }
                if (matrix[row + 1][column + 1] != null && matrix[row + 1][column + 1].color == Piece.Black) {
                    char first = (char) ('a' + column);
                    if (!Board.checkChess(Board.generateNextBoard(matrix,
                        new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('2' + row))), Piece.White)) {
                        if (row == 6) {
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('2' + row) + "q"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('2' + row) + "r"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('2' + row) + "n"));
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('2' + row) + "b"));
                        }
                        else {
                            moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column + 1) + (char)('2' + row)));
                        }
                    }
                }
                // En passant move
                if (matrix[4][column] == this && matrix[4][column - 1] != null && matrix[4][column - 1].type == Piece.Pawn) {
                    if (matrix[4][column - 1].color == Piece.White && ((Pawn) matrix[4][column - 1]).enPassantEligible()) {
                        char first = (char) ('a' + column);
                        if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix,
                        new String(Character.toString(first) + '5' + (char)('a' + column - 1) + "5")),
                        new String(Character.toString((char)('a' + column - 1)) + '5' + (char)('a' + column - 1) + "6")), Piece.White)) {
                        moves.add(new String(Character.toString(first) + '5' + (char)('a' + column - 1) + "6"));
                        }
                    }
                }
                if (matrix[4][column] == this && matrix[4][column + 1] != null && matrix[4][column + 1].type == Piece.Pawn) {
                    if (matrix[4][column + 1].color == Piece.White && ((Pawn) matrix[4][column + 1]).enPassantEligible()) {
                        char first = (char) ('a' + column);
                        if (!Board.checkChess(Board.generateNextBoard(Board.generateNextBoard(matrix,
                        new String(Character.toString(first) + '5' + (char)('a' + column + 1) + "5")),
                        new String(Character.toString((char)('a' + column + 1)) + '5' + (char)('a' + column + 1) + "6")), Piece.White)) {
                            moves.add(new String(Character.toString(first) + '5' + (char)('a' + column + 1) + "6"));
                        }
                    }
                }
            }
            // If we can't take any pieces, we try to advance with 1 or two positions.
            if (row == 1) {
                if (matrix[2][column] == null) {
                    if (matrix[3][column] == null) {
                        char first = (char) ('a' + column);
                        if (!Board.checkChess(Board.generateNextBoard(matrix,
                            new String(Character.toString(first) + (char)'2' + (char)('a' + column) + (char)'4')), Piece.White)) {
                            moves.add(new String(Character.toString(first) + (char)'2' + (char)('a' + column) + (char)'4'));
                        }
                    }
                    else {
                        char first = (char) ('a' + column);
                        if (!Board.checkChess(Board.generateNextBoard(matrix,
                            new String(Character.toString(first) + (char)'2' + (char)('a' + column) + (char)'3')), Piece.White)) {
                            moves.add(new String(Character.toString(first) + (char)'2' + (char)('a' + column) + (char)'3'));
                        }
                    }
                }
            }
            if (matrix[row + 1][column] == null) {
                char first = (char) ('a' + column);
                if (!Board.checkChess(Board.generateNextBoard(matrix,
                            new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('2' + row))), Piece.White)) {
                    if (row == 6) {
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('2' + row) + "q"));
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('2' + row) + "r"));
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('2' + row) + "n"));
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('2' + row) + "b"));
                    }
                    else {
                        moves.add(new String(Character.toString(first) + (char)('1' + row) + (char)('a' + column) + (char)('2' + row)));
                    }
                }
            }
        }
    }

    @Override
    public Piece makeCopy() {
        return new Pawn(color, row, column);
    }

    public boolean enPassantEligible() {
        if (enPassant == 1)
            return true;
        return false;
    }

    public void makeEnPassantNotEligible() {
        enPassant = 0;
    }
}

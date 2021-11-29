import java.util.ArrayList;
import java.util.Random;

public class Board {
    public Piece[][] matrix; // board state matrix
    public static Board instance = null;
    public ArrayList<Piece> piecesBlack;
    public ArrayList<Piece> piecesWhite;

    // The 2 pawns moved at stage 1 of the project.
    public King black_king;
    public King white_king;

    // chess counters
    int black_chess_counter;
    int white_chess_counter;

    // create board
    private Board() {
        matrix = new Piece[8][8];
        piecesBlack = new ArrayList<>();
        piecesWhite = new ArrayList<>();

        initialize();
    }

    public void initialize() {
        // chess Coutners
        black_chess_counter = 0;
        white_chess_counter = 0;

        // Pawns
        for (int i = 0; i < 8; i++) {
            Pawn p1 = new Pawn(Piece.White,1, i);
            Pawn p2 = new Pawn(Piece.Black, 6, i);
            piecesWhite.add(p1);
            piecesBlack.add(p2);
            matrix[1][i] = p1;
            matrix[6][i] = p2;
        }

        // Kings and Queens
        King k1 = new King(Piece.White, 0, 4);
        white_king = k1;
        King k2 = new King(Piece.Black, 7, 4);
        black_king = k2;
        Queen q1 = new Queen(Piece.White, 0, 3);
        Queen q2 = new Queen(Piece.Black, 7, 3);
        piecesBlack.add(k2);
        piecesBlack.add(q2);
        piecesWhite.add(k1);
        piecesWhite.add(q1);
        matrix[0][4] = k1;
        matrix[7][4] = k2;
        matrix[0][3] = q1;
        matrix[7][3] = q2;

        // Knights
        Knight c1 = new Knight(Piece.White, 0, 1);
        Knight c2 = new Knight(Piece.White, 0, 6);
        Knight c3 = new Knight(Piece.Black, 7, 1);
        Knight c4 = new Knight(Piece.Black, 7, 6);
        piecesBlack.add(c3);
        piecesBlack.add(c4);
        piecesWhite.add(c1);
        piecesWhite.add(c2);
        matrix[0][1] = c1;
        matrix[0][6] = c2;
        matrix[7][1] = c3;
        matrix[7][6] = c4;

        // Bishops
        Bishop b1 = new Bishop(Piece.White,0,2);
        Bishop b2 = new Bishop(Piece.White,0,5);
        Bishop b3 = new Bishop(Piece.Black,7,2);
        Bishop b4 = new Bishop(Piece.Black,7,5);
        piecesBlack.add(b3);
        piecesBlack.add(b4);
        piecesWhite.add(b1);
        piecesWhite.add(b2);
        matrix[0][2] = b1;
        matrix[0][5] = b2;
        matrix[7][2] = b3;
        matrix[7][5] = b4;

        // Rooks
        Rook r1 = new Rook(Piece.White,0,0);
        Rook r2 = new Rook(Piece.White,0,7);
        Rook r3 = new Rook(Piece.Black,7,0);
        Rook r4 = new Rook(Piece.Black,7,7);
        piecesBlack.add(r3);
        piecesBlack.add(r4);
        piecesWhite.add(r1);
        piecesWhite.add(r2);
        matrix[0][0] = r1;
        matrix[0][7] = r2;
        matrix[7][0] = r3;
        matrix[7][7] = r4;
    }

    public ArrayList<Pawn> getBlackPawns() {
        ArrayList<Pawn> blackPawns = new ArrayList<>();
        for (Piece piece : piecesBlack) {
            if (piece.type == Piece.Pawn) {
                blackPawns.add((Pawn) piece);
            }
        }
        return blackPawns;
    }

    public ArrayList<Pawn> getWhitePawns() {
        ArrayList<Pawn> whitePawns = new ArrayList<>();
        for (Piece piece : piecesWhite) {
            if (piece.type == Piece.Pawn) {
                whitePawns.add((Pawn) piece);
            }
        }
        return whitePawns;
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void reinitialize() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matrix[i][j] = null;
            }
        }
        piecesBlack.clear();
        piecesWhite.clear();

        initialize();
    }

    // Function that updates the internal representation of the board.
    public void update(String command) {
        Piece toMove = matrix[command.charAt(1) - '1'][command.charAt(0) - 'a'];
        Piece whereToMove = matrix[command.charAt(3) - '1'][command.charAt(2) - 'a'];

        if (toMove.type == Piece.Rook) {
            ((Rook) toMove).rockadable = false;
        }
        if (toMove.type == Piece.King) {
            ((King) toMove).rockadable = false;
        }

        // check for rocada
        // white side
        if (command.equals("e1c1") && toMove.type == Piece.King) {
            King king = (King) matrix[0][4];
            Rook rook = (Rook) matrix[0][0];

            matrix[0][2] = king;
            matrix[0][3] = rook;

            king.column = 2;
            rook.column = 3;

            matrix[0][0] = null;
            matrix[0][4] = null;
            return;
        }
        if (command.equals("e1g1") && toMove.type == Piece.King) {
            King king = (King) matrix[0][4];
            Rook rook = (Rook) matrix[0][7];

            matrix[0][6] = king;
            matrix[0][5] = rook;

            king.column = 6;
            rook.column = 5;

            matrix[0][7] = null;
            matrix[0][4] = null;
            return;
        }

        // black side
        if (command.equals("e8c8") && toMove.type == Piece.King) {
            King king = (King) matrix[7][4];
            Rook rook = (Rook) matrix[7][0];

            matrix[7][2] = king;
            matrix[7][3] = rook;

            king.column = 2;
            rook.column = 3;

            matrix[7][0] = null;
            matrix[7][4] = null;
            return;
        }
        if (command.equals("e8g8") && toMove.type == Piece.King) {
            King king = (King) matrix[7][4];
            Rook rook = (Rook) matrix[7][7];

            matrix[7][6] = king;
            matrix[7][5] = rook;

            king.column = 6;
            rook.column = 5;

            matrix[7][7] = null;
            matrix[7][4] = null;
            return;
        }

        // check if it is an en-passant move and remove element
        if (whereToMove == null && toMove.type == Piece.Pawn) {
            if (toMove.color == Piece.Black) {
                Piece whatToRemove = matrix[command.charAt(3) - '1' + 1][command.charAt(2) - 'a'];
                if (whatToRemove != null && whatToRemove.type == Piece.Pawn &&
                ((Pawn) whatToRemove).enPassant == 1) {
                    piecesWhite.remove(whatToRemove);
                    matrix[command.charAt(3) - '1' + 1][command.charAt(2) - 'a'] = null;
                }
            } else {
                Piece whatToRemove = matrix[command.charAt(3) - '1' - 1][command.charAt(2) - 'a'];
                if (whatToRemove != null && whatToRemove.type == Piece.Pawn &&
                ((Pawn) whatToRemove).enPassant == 1) {
                    piecesBlack.remove(whatToRemove);
                    matrix[command.charAt(3) - '1' - 1][command.charAt(2) - 'a'] = null;
                }
            }
        }

        if (Math.abs(command.charAt(1) - command.charAt(3)) == 2 && command.charAt(0) == command.charAt(2)) {
            if (toMove.type == Piece.Pawn)
                ((Pawn) toMove).enPassant = 1;
        } else {
            // for White Pawns
            if (toMove.color == Piece.White) {
                for (int i = 0; i < piecesWhite.size(); i++) {
                    if (piecesWhite.get(i).type == Piece.Pawn)
                        ((Pawn) piecesWhite.get(i)).makeEnPassantNotEligible();
                }
            }
            // for Black Pawns
            if (toMove.color == Piece.White) {
                for (int i = 0; i < piecesBlack.size(); i++) {
                    if (piecesBlack.get(i).type == Piece.Pawn)
                        ((Pawn) piecesBlack.get(i)).makeEnPassantNotEligible();
                }
            }
        }

        toMove.row = command.charAt(3) - '1';
        toMove.column = command.charAt(2) - 'a';

        // Check if we need to remove pieces at this move.
        if (whereToMove != null) {
            if (whereToMove.color == Piece.Black) {
                piecesBlack.remove(whereToMove);
            }
            else {
                piecesWhite.remove(whereToMove);
            }
        }

        // Check if we need to add a queen at this move.
        if (toMove.color == Piece.Black && toMove.row == 0 && toMove.type == Piece.Pawn) {
            if (command.charAt(4) == 'q') {
                piecesBlack.remove(toMove);
                toMove = new Queen(Piece.Black, toMove.row, toMove.column);
                piecesBlack.add(toMove);
            }
            else if (command.charAt(4) == 'r') {
                piecesBlack.remove(toMove);
                toMove = new Rook(Piece.Black, toMove.row, toMove.column);
                ((Rook) toMove).rockadable = false;
                piecesBlack.add(toMove);
            }
            else if (command.charAt(4) == 'n') {
                piecesBlack.remove(toMove);
                toMove = new Knight(Piece.Black, toMove.row, toMove.column);
                piecesBlack.add(toMove);
            }
            else {
                piecesBlack.remove(toMove);
                toMove = new Bishop(Piece.Black, toMove.row, toMove.column);
                piecesBlack.add(toMove);
            }
        }
        if (toMove.color == Piece.White && toMove.row == 7 && toMove.type == Piece.Pawn) {
            if (command.charAt(4) == 'q') {
                piecesWhite.remove(toMove);
                toMove = new Queen(Piece.White, toMove.row, toMove.column);
                piecesWhite.add(toMove);
            }
            else if (command.charAt(4) == 'r') {
                piecesWhite.remove(toMove);
                toMove = new Rook(Piece.White, toMove.row, toMove.column);
                ((Rook) toMove).rockadable = false;
                piecesWhite.add(toMove);
            }
            else if (command.charAt(4) == 'n') {
                piecesWhite.remove(toMove);
                toMove = new Knight(Piece.White, toMove.row, toMove.column);
                piecesWhite.add(toMove);
            }
            else {
                piecesWhite.remove(toMove);
                toMove = new Bishop(Piece.White, toMove.row, toMove.column);
                piecesWhite.add(toMove);
            }
        }

        matrix[command.charAt(1) - '1'][command.charAt(0) - 'a'] = null;
        matrix[command.charAt(3) - '1'][command.charAt(2) - 'a'] = toMove;
    }

    /*
    Function that sends the actual move command to the xboard and updates the
    internal representation of the board.
     */
    public void move(String command) {
        System.out.println("move " + command);
        update(command);
    }

    /*
    Function that checks if we can make a new valid move at this moment.
     */
    public void nextMove(int color) {
        Evaluation bestMove = minmax(matrix, color, 1, 4, black_chess_counter, white_chess_counter,
                -Float.MAX_VALUE, Float.MAX_VALUE, color);
        if (bestMove.move == null) {
            System.out.println("resign");
        }
        else {
            move(bestMove.move);
        }
    }
    public ArrayList<String> generateMoves (Piece[][] state, int color) {
        ArrayList<Piece> pieseNegre = new ArrayList<>();
        ArrayList<Piece> pieseAlbe = new ArrayList<>();
        King rege_negru = null;
        King rege_alb = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (state[i][j] != null) {
                    if (state[i][j].color == Piece.Black) {
                        pieseNegre.add(state[i][j]);
                        if (state[i][j].type == Piece.King) {
                            rege_negru = (King) state[i][j];
                        }
                    }
                    else {
                        pieseAlbe.add(state[i][j]);
                        if (state[i][j].type == Piece.King) {
                            rege_alb = (King) state[i][j];
                        }
                    }
                }
            }
        }
        if (color == Piece.Black) {
            ArrayList<String> moves = new ArrayList<>();
            if (!checkChess(state, Piece.Black)) {
                for (Piece piece : pieseNegre) {
                    piece.move(moves, state);
                }
            }
            else {
                rege_negru.rockadable = false;
                for (Piece piece : pieseNegre) {
                    piece.move(moves, state);
                }
            }
            return moves;
//            if (moves.isEmpty()) {
//                System.out.println("resign");
//            }
//            else {
//                if (moves.contains("e8c8")) {
//                    move("e8c8");
//                }
//                else if (moves.contains("e8g8")) {
//                    move("e8g8");
//                }
//                else {
//                    int random_move = new Random().nextInt(moves.size());
//                    String chosen = moves.get(random_move);
//                    move(chosen);
//                }
//            }
        }
        else {
            ArrayList<String> moves = new ArrayList<>();
            if (!checkChess(matrix, Piece.White)) {
                for (Piece piece : pieseAlbe) {
                    piece.move(moves, state);
                }
            }
            else {
                rege_alb.rockadable = false;
                for (Piece piece : pieseAlbe) {
                    piece.move(moves, state);
                }
            }
            return moves;
//            if (moves.isEmpty()) {
//                System.out.println("resign");
//            }
//            else {
//                if (moves.contains("e1c1")) {
//                    move("e1c1");
//                }
//                else if (moves.contains("e1g1")) {
//                    move("e1g1");
//                }
//                else {
//                    int random_move = new Random().nextInt(moves.size());
//                    String chosen = moves.get(random_move);
//                    move(chosen);
//                }
//            }
        }
    }

    /*
    Function that prints the internal representation of the board (for debugging).
     */
    public void print() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Piece piece = matrix[i][j];
                if (piece == null) {
                    System.out.print("_ ");
                }
                else {
                    if (piece.color == Piece.Black) {
                        if (piece.type == Piece.Pawn) {
                            System.out.print("P ");
                        }
                        else if (piece.type == Piece.King) {
                            System.out.print("K ");
                        }
                        else if (piece.type == Piece.Queen) {
                            System.out.print("Q ");
                        }
                        else if (piece.type == Piece.Knight) {
                            System.out.print("C ");
                        }
                        else if (piece.type == Piece.Bishop) {
                            System.out.print("B ");
                        }
                        else {
                            System.out.print("R ");
                        }
                    }
                    else {
                        if (piece.type == Piece.Pawn) {
                            System.out.print("p ");
                        }
                        else if (piece.type == Piece.King) {
                            System.out.print("k ");
                        }
                        else if (piece.type == Piece.Queen) {
                            System.out.print("q ");
                        }
                        else if (piece.type == Piece.Knight) {
                            System.out.print("c ");
                        }
                        else if (piece.type == Piece.Bishop) {
                            System.out.print("b ");
                        }
                        else {
                            System.out.print("r ");
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Black pieces:");
        for (Piece piece : piecesBlack) {
            if (piece.type == Piece.Pawn) {
                System.out.print("P ");
            }
            else if (piece.type == Piece.King) {
                System.out.print("K ");
            }
            else if (piece.type == Piece.Queen) {
                System.out.print("Q ");
            }
            else if (piece.type == Piece.Knight) {
                System.out.print("C ");
            }
            else if (piece.type == Piece.Bishop) {
                System.out.print("B ");
            }
            else {
                System.out.print("R ");
            }
        }
        System.out.println();
        System.out.println("White pieces:");
        for (Piece piece : piecesWhite) {
            if (piece.type == Piece.Pawn) {
                System.out.print("p ");
            }
            else if (piece.type == Piece.King) {
                System.out.print("k ");
            }
            else if (piece.type == Piece.Queen) {
                System.out.print("q ");
            }
            else if (piece.type == Piece.Knight) {
                System.out.print("c ");
            }
            else if (piece.type == Piece.Bishop) {
                System.out.print("b ");
            }
            else {
                System.out.print("r ");
            }
        }
    }
    public static Boolean checkChess(Piece[][] chessBoard, int color) {
        Boolean isCheck = false;
        Piece king = null;
        //Divide cases in two colors
        if(color == Piece.Black) {
            //get the king piece
            for(int i = 0; i < chessBoard.length && king == null; i++) {
                for(Piece p: chessBoard[i]) {
                    if (p != null && p.type == Piece.King && p.color == Piece.Black) {
                        king = p;
                        break;
                    }
                }
            }
                if (king == null)
                    return true;
            //check for pawns || bishops || queen
                if(king.row != 0) {
                    Piece bottomLeft, bottomRight;
                    if(king.column != 0) {
                        bottomLeft = chessBoard[king.row-1][king.column-1];
                        if(bottomLeft != null && bottomLeft.color == Piece.White &&
                                (bottomLeft.type == Piece.Queen ||
                                 bottomLeft.type == Piece.Bishop ||
                                 bottomLeft.type == Piece.Pawn)
                        ) {
                            return true;
                        }
                    }
                    if(king.column != 7) {
                        bottomRight = chessBoard[king.row-1][king.column+1];
                        if(bottomRight != null && bottomRight.color == Piece.White &&
                                (bottomRight.type == Piece.Queen ||
                                        bottomRight.type == Piece.Bishop ||
                                        bottomRight.type == Piece.Pawn)
                        ) {
                            return true;
                        }
                    }
                }
            //check diagonals for bishops || queen
                int x, y; //axis coordinates
                //check upper left
                x = king.column - 1;
                y = king.row + 1;
                while (true) {
                    if(x < 0 || y > 7) {
                        //no piece found on the diagonal
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.White &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Bishop)) {
                            return true;
                        } else {
                            //another piece is blocking the diagonal
                            break;
                        }
                    }
                    x--;
                    y++;
                }
                //check upper right
                x = king.column + 1;
                y = king.row + 1;
                while (true) {
                    if(x > 7 || y > 7) {
                        //no piece found on the diagonal
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.White &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Bishop)) {
                            return true;
                        } else {
                            //another piece is blocking the diagonal
                            break;
                        }
                    }
                    x++;
                    y++;
                }
                //check lower right
                x = king.column + 1;
                y = king.row - 1;
                while (true) {
                    if(x > 7 || y < 0) {
                        //no piece found on the diagonal
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.White &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Bishop)) {
                            return true;
                        } else {
                            //another piece is blocking the diagonal
                            break;
                        }
                    }
                    x++;
                    y--;
                }
                //check lower left
                x = king.column - 1;
                y = king.row - 1;
                while (true) {
                    if(x < 0 || y < 0) {
                        //no piece found on the diagonal
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.White &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Bishop)) {
                            return true;
                        } else {
                            //another piece is blocking the diagonal
                            break;
                        }
                    }
                    x--;
                    y--;
                }
            //check cross for rook || queen
                //check upper line
                x = king.column;
                y = king.row + 1;
                while (true) {
                    if(y > 7) {
                        //no piece found in the line
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.White &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Rook)) {
                            return true;
                        } else {
                            //another piece is blocking the line
                            break;
                        }
                    }
                    y++;
                }
                //check lower line
                x = king.column;
                y = king.row - 1;
                while (true) {
                    if(y < 0) {
                        //no piece found in the line
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.White &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Rook)) {
                            return true;
                        } else {
                            //another piece is blocking the line
                            break;
                        }
                    }
                    y--;
                }
                //check left line
                x = king.column - 1;
                y = king.row;
                while (true) {
                    if(x < 0) {
                        //no piece found in the line
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.White &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Rook)) {
                            return true;
                        } else {
                            //another piece is blocking the line
                            break;
                        }
                    }
                    x--;
                }
                //check right line
                x = king.column + 1;
                y = king.row;
                while (true) {
                    if(x > 7) {
                        //no piece found in the line
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.White &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Rook)) {
                            return true;
                        } else {
                            //another piece is blocking the line
                            break;
                        }
                    }
                    x++;
                }
            //check knight moves
                // 1
                x = king.column - 2;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                    chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 2
                x = king.column - 1;
                y = king.row - 2;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 3
                x = king.column + 1;
                y = king.row - 2;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 4
                x = king.column + 2;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 5
                x = king.column + 2;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 6
                x = king.column + 1;
                y = king.row + 2;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 7
                x = king.column - 1;
                y = king.row + 2;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 8
                x = king.column - 2;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }

                //Check if White King generates check
                //1
                x = king.column - 1;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //2
                x = king.column;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //3
                x = king.column + 1;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //4
                x = king.column - 1;
                y = king.row;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //5
                x = king.column + 1;
                y = king.row;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //6
                x = king.column - 1;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //7
                x = king.column;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //8
                x = king.column + 1;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.White && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
        } else {
            //get the king piece
            for(int i = 0; i < chessBoard.length && king == null; i++) {
                for(Piece p: chessBoard[i]) {
                    if (p != null && p.type == Piece.King && p.color == Piece.White) {
                        king = p;
                        break;
                    }
                }
            }
            if (king == null)
                return true;
            //check for pawns || bishops || queen
                if(king.row != 7) {
                    Piece upperLeft, upperRight;
                    if(king.column != 0) {
                        upperLeft = chessBoard[king.row+1][king.column-1];
                        if(upperLeft != null && upperLeft.color == Piece.Black &&
                                (upperLeft.type == Piece.Queen ||
                                 upperLeft.type == Piece.Bishop ||
                                 upperLeft.type == Piece.Pawn)
                        ) {
                            return true;
                        }
                    }
                    if(king.column != 7) {
                        upperRight = chessBoard[king.row+1][king.column+1];
                        if(upperRight != null && upperRight.color == Piece.Black &&
                                (upperRight.type == Piece.Queen ||
                                 upperRight.type == Piece.Bishop ||
                                 upperRight.type == Piece.Pawn)
                        ) {
                            return true;
                        }
                    }
                }
            //check diagonals for bishops || queen
                int x, y; //axis coordinates
                //check upper left
                x = king.column - 1;
                y = king.row + 1;
                for(int i = 1; i < 8; i++) {
                    if(x < 0 || y > 7) {
                        //no piece found on the diagonal
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.Black &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Bishop)) {
                            return true;
                        } else {
                            //another piece is blocking the diagonal
                            break;
                        }
                    }
                    x--;
                    y++;
                }
                //check upper right
                x = king.column + 1;
                y = king.row + 1;
                for(int i = 1; i < 8; i++) {
                    if(x > 7 || y > 7) {
                        //no piece found on the diagonal
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.Black &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Bishop)) {
                            return true;
                        } else {
                            //another piece is blocking the diagonal
                            break;
                        }
                    }
                    x++;
                    y++;
                }
                //check lower right
                x = king.column + 1;
                y = king.row - 1;
                for(int i = 1; i < 8; i++) {
                    if(x > 7 || y < 0) {
                        //no piece found on the diagonal
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.Black &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Bishop)) {
                            return true;
                        } else {
                            //another piece is blocking the diagonal
                            break;
                        }
                    }
                    x++;
                    y--;
                }
                //check lower left
                x = king.column - 1;
                y = king.row - 1;
                for(int i = 1; i < 8; i++) {
                    if(x < 0 || y < 0) {
                        //no piece found on the diagonal
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.Black &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Bishop)) {
                            return true;
                        } else {
                            //another piece is blocking the diagonal
                            break;
                        }
                    }
                    x--;
                    y--;
                }
            //check cross for rook || queen
                //check upper line
                x = king.column;
                y = king.row + 1;
                while (true) {
                    if(y > 7) {
                        //no piece found in the line
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.Black &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Rook)) {
                            return true;
                        } else {
                            //another piece is blocking the line
                            break;
                        }
                    }
                    y++;
                }
                //check lower line
                x = king.column;
                y = king.row - 1;
                while (true) {
                    if(y < 0) {
                        //no piece found in the line
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.Black &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Rook)) {
                            return true;
                        } else {
                            //another piece is blocking the line
                            break;
                        }
                    }
                    y--;
                }
                //check left line
                x = king.column - 1;
                y = king.row;
                while (true) {
                    if(x < 0) {
                        //no piece found in the line
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.Black &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Rook)) {
                            return true;
                        } else {
                            //another piece is blocking the line
                            break;
                        }
                    }
                    x--;
                }
                //check right line
                x = king.column + 1;
                y = king.row;
                while (true) {
                    if(x > 7) {
                        //no piece found in the line
                        break;
                    }
                    //found a piece
                    if(chessBoard[y][x] != null) {
                        Piece pieceFound = chessBoard[y][x];
                        if(pieceFound.color == Piece.Black &&
                                (pieceFound.type == Piece.Queen || pieceFound.type == Piece.Rook)) {
                            return true;
                        } else {
                            //another piece is blocking the line
                            break;
                        }
                    }
                    x++;
                }
            //check knight moves
                // 1
                x = king.column - 2;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 2
                x = king.column - 1;
                y = king.row - 2;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 3
                x = king.column + 1;
                y = king.row - 2;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 4
                x = king.column + 2;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 5
                x = king.column + 2;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 6
                x = king.column + 1;
                y = king.row + 2;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 7
                x = king.column - 1;
                y = king.row + 2;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }
                // 8
                x = king.column - 2;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.Knight) {
                    return true;
                }

                //Check if Black King generates check
                //1
                x = king.column - 1;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //2
                x = king.column;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //3
                x = king.column + 1;
                y = king.row + 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //4
                x = king.column - 1;
                y = king.row;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //5
                x = king.column + 1;
                y = king.row;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //6
                x = king.column - 1;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //7
                x = king.column;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
                //8
                x = king.column + 1;
                y = king.row - 1;
                if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && chessBoard[y][x] != null &&
                        chessBoard[y][x].color == Piece.Black && chessBoard[y][x].type == Piece.King) {
                    return true;
                }
        }
        return false;
    }

    public static Piece[][] generateNextBoard(Piece[][] board, String move){
        Piece[][] futureBoard = new Piece[8][8];
        //make copies of every piece from the board
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                if(board[i][j] != null)
                    futureBoard[i][j] = board[i][j].makeCopy();
            }
        }

        Piece toMove = futureBoard[move.charAt(1) - '1'][move.charAt(0) - 'a'];
        // Remove old piece
        if (toMove == null) {
            for(int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++){
                    System.err.print(futureBoard[i][j] + " ");
                }System.err.println();
            }
        }
        futureBoard[move.charAt(1) - '1'][move.charAt(0) - 'a'] = null;

        // Check for pawn turns into new pieces
        if(toMove.type == Piece.Pawn) {
            char choice = 'd'; // d stands for default
            if(move.length() == 5) {
                choice = move.charAt(4);
            }
            if(toMove.color == Piece.Black) {
                if(toMove.row == 1) {
                    switch (choice) {
                        case 'q':{
                            futureBoard[toMove.row - 1][toMove.column] =
                                    new Queen(Piece.Black, 0, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                        case 'r':{
                            futureBoard[toMove.row - 1][toMove.column] =
                                    new Rook(Piece.Black, 0, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                        case 'n':{
                            futureBoard[toMove.row - 1][toMove.column] =
                                    new Knight(Piece.Black, 0, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                        case 'b':{
                            futureBoard[toMove.row - 1][toMove.column] =
                                    new Bishop(Piece.Black, 0, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                        // if no parameter is set change to queen by default
                        default: {
                            futureBoard[toMove.row - 1][toMove.column] =
                                    new Queen(Piece.Black, 0, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                    }
                }
            } else {
                if(toMove.row == 6) {
                    switch (choice) {
                        case 'q':{
                            futureBoard[toMove.row + 1][toMove.column] =
                                    new Queen(Piece.White, 7, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                        case 'r':{
                            futureBoard[toMove.row + 1][toMove.column] =
                                    new Rook(Piece.White, 7, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                        case 'n':{
                            futureBoard[toMove.row + 1][toMove.column] =
                                    new Knight(Piece.White, 7, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                        case 'b':{
                            futureBoard[toMove.row + 1][toMove.column] =
                                    new Bishop(Piece.White, 7, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                        // if no parameter is set change to queen by default
                        default: {
                            futureBoard[toMove.row + 1][toMove.column] =
                                    new Queen(Piece.White, 7, toMove.column);
                            futureBoard[toMove.row][toMove.column] = null;

                            return futureBoard;
                        }
                    }
                }
            }
        }
        // Make normal move
        futureBoard[move.charAt(3) - '1'][move.charAt(2) - 'a'] = toMove;
        toMove.row = move.charAt(3) - '1';
        toMove.column = move.charAt(2) - 'a';
        if (toMove.type == Piece.King) {
            ((King) toMove).rockadable = false;
        }
        if (toMove.type == Piece.Rook) {
            ((Rook) toMove).rockadable = false;
        }

        return futureBoard;
    }

    Evaluation minmax(Piece[][] boardState, int color, int player, int depth, int chessBlack, int chessWhite,
                      float alfa, float beta, int masterColor) {
        if (depth == 0) {
            System.out.println("Reached heuristic");
            return new Evaluation(evaluateBoard(boardState, masterColor, chessBlack, chessWhite), null);
        }

        ArrayList<String> possibleMoves = generateMoves(boardState, color);
        Piece[][] futureBoard;
        Evaluation moveEvaluation = null;
        Evaluation bestEvaluation = new Evaluation();
        Float value;
        //Setting the initial value for the alfa-beta
        if (player >= 0) {
            value = -Float.MAX_VALUE;
        } else {
            value = Float.MAX_VALUE;
        }

        bestEvaluation.profit = (player < 0) ? Float.MAX_VALUE : -Float.MAX_VALUE;
        for (String currentMove : possibleMoves) {
            futureBoard = generateNextBoard(boardState, currentMove);

            if (color == Piece.Black) {
                if (checkChess(futureBoard, Piece.White)) {
                    if (chessWhite + 1 == 3) {
                        if (player < 0)
                            return new Evaluation(-Float.MAX_VALUE, null);
                        else return new Evaluation(Float.MAX_VALUE, null);
                    }
                    moveEvaluation = minmax(futureBoard, Piece.White, -player, depth - 1, chessBlack,
                            chessWhite + 1, alfa, beta, masterColor);
                }
                else {
                    moveEvaluation = minmax(futureBoard, Piece.White, -player, depth - 1, chessBlack,
                            chessWhite, alfa, beta, masterColor);
                }
            } else {
                if (checkChess(futureBoard, Piece.Black)) {
                    if (chessBlack + 1 == 3) {
                        if (player < 0)
                            return new Evaluation(-Float.MAX_VALUE, null);
                        else return new Evaluation(Float.MAX_VALUE, null);
                    }
                    moveEvaluation = minmax(futureBoard, Piece.Black, -player, depth - 1,
                            chessBlack + 1, chessWhite, alfa, beta, masterColor);
                }
                else {
                    moveEvaluation = minmax(futureBoard, Piece.Black, -player, depth - 1, chessBlack,
                            chessWhite, alfa, beta, masterColor);
                }
            }

            if (player < 0) {
                value = Math.min(value, moveEvaluation.profit);
                beta = Math.min(beta, value);
                if (moveEvaluation.profit < bestEvaluation.profit) {
                    bestEvaluation.profit = moveEvaluation.profit;
                    bestEvaluation.move = new String(currentMove);
                }
                if(beta <= alfa) {
                    return bestEvaluation;
                }
            }
            else {
                value = Math.max(value, moveEvaluation.profit);
                alfa = Math.max(alfa, value);
                if (moveEvaluation.profit > bestEvaluation.profit) {
                    bestEvaluation.profit = moveEvaluation.profit;
                    bestEvaluation.move = new String(currentMove);
                }
                if(alfa >= beta) {
                    return bestEvaluation;
                }
            }
        }

        return bestEvaluation;
    }

    //Heuristic function to evaluate a board
    public static float evaluateBoard(Piece boardState[][], int color,
                                      int chessCountBlack, int chessCountWhite) {
        if (color == Piece.Black) {
            //3rd chess will finish the game
            if(chessCountWhite == 3) {
                return Float.MAX_VALUE;
            }
            if(chessCountBlack == 3) {
                return -Float.MAX_VALUE;
            }
            return computeCost(boardState, Piece.Black, chessCountBlack, chessCountWhite) -
                    computeCost(boardState, Piece.White, chessCountBlack, chessCountWhite);
        } else {
            //3rd chess will finish the game
            if(chessCountBlack == 3) {
                return Float.MAX_VALUE;
            }
            if(chessCountWhite == 3) {
                return -Float.MAX_VALUE;
            }
            return computeCost(boardState, Piece.White, chessCountBlack, chessCountWhite) -
                    computeCost(boardState, Piece.Black, chessCountBlack, chessCountWhite);
        }
    }

    public static float computeCost(Piece boardState[][], int color,
                                    int chessCountBlack, int chessCountWhite) {
        float boardValue = 0;
        Piece king = null;
        ArrayList<Piece> pieces = new ArrayList<>();

        if(color == Piece.Black) {
            // Get all the Black pieces from the board
            for(int i = 0; i < boardState.length; i++) {
                for(Piece p: boardState[i]) {
                    if (p != null && p.color == Piece.Black) {
                        if(p.type != Piece.King) {
                            pieces.add(p);
                        } else {
                            king = p;
                        }
                    }
                }
            }
            // If king is missing it was checkMate
            if(king == null) {
                return -Float.MAX_VALUE;
            }
            for(Piece p: pieces) {
                boardValue += p.value;
                // If a piece is closer to the king, it becomes more valuable
                /*if(getDistanceFromKing(p, king) == 1) {
                    boardValue += 0.2 * p.value;
                } else if (getDistanceFromKing(p, king) == 2) {
                    boardValue += 0.1 * p.value;
                }*/
                // Special case in which a pawn which will reach the end will turn into
                //a more valuable piece
//                if(p.type == Piece.Pawn && p.row <= 2 && isPawnClear(boardState, (Pawn) p)) {
//                    switch (p.row) {
//                        case 2: {
//                            boardValue += 10;
//                            break;
//                        }
//                        case 1: {
//                            boardValue += 25;
//                            break;
//                        }
//                    }
//                }
            }
            boardValue += checkInfluence(chessCountBlack, chessCountWhite);
        } else {
            // Get all the White pieces from the board
            for(int i = 0; i < boardState.length; i++) {
                for(Piece p: boardState[i]) {
                    if (p != null && p.color == Piece.White) {
                        if(p.type != Piece.King) {
                            pieces.add(p);
                        } else {
                            king = p;
                        }
                    }
                }
            }
            // If king is missing it was checkMate
            if(king == null) {
                return -Float.MAX_VALUE;
            }
            for(Piece p: pieces) {
                boardValue += p.value;
                // If a piece is closer to the king, it becomes more valuable
                /* if(getDistanceFromKing(p, king) == 1) {
                    boardValue += 0.2 * p.value;
                } else if (getDistanceFromKing(p, king) == 2) {
                    boardValue += 0.1 * p.value;
                } */
                // Special case in which a pawn which will reach the end will turn into
                //a more valuable piece
//                if(p.type == Piece.Pawn && p.row >= 5 && isPawnClear(boardState, (Pawn) p)) {
//                    switch (p.row) {
//                        case 5: {
//                            boardValue += 10;
//                            break;
//                        }
//                        case 6: {
//                            boardValue += 25;
//                            break;
//                        }
//                    }
//                }
            }
            boardValue += checkInfluence(chessCountWhite, chessCountBlack);
        }
        return boardValue;
    }
    //Checks if a pawn has a free way to become  a new piece
    private static boolean isPawnClear(Piece[][] matrix, Pawn pawn) {
        int row = pawn.row, auxRow;
        int column = pawn.column;
        boolean ok = true;

        if(pawn.color == Piece.Black) {
            auxRow = row - 1;
            for(auxRow = row - 1; auxRow >= 0; auxRow--) {
                if(matrix[auxRow][column] != null &&
                        matrix[auxRow][column].color == Piece.White) {
                    ok = false;
                    break;
                }
            }
            return ok;
        } else {
            auxRow = row + 1;
            for(auxRow = row - 1; auxRow <= 7; auxRow++) {
                if(matrix[auxRow][column] != null &&
                        matrix[auxRow][column].color == Piece.Black) {
                    ok = false;
                    break;
                }
            }
            return ok;
        }
    }

    // Return the maximum distance from the king on either x or y axis
    private static int getDistanceFromKing(Piece piece, Piece king) {
        return Math.max(Math.abs(piece.column - king.column),
                Math.abs(piece.row - king.row));
    }

    // Returns the number which will change the total value
    private static float checkInfluence(int home, int guest) {
        if(home == 0) {
            switch (guest) {
                case 0:{
                    return 0;
                }
                case 1:{
                    return 7;
                }
                case 2:{
                    return 25;
                }
            }
        }
        if(home == 1) {
            switch (guest) {
                case 0:{
                    return -7;
                }
                case 1:{
                    return 0;
                }
                case 2:{
                    return 7;
                }
            }
        }
        if(home == 2) {
            switch (guest) {
                case 0:{
                    return -25;
                }
                case 1:{
                    return -7;
                }
                case 2:{
                    return 0;
                }
            }
        }
        return 1;
    }
}
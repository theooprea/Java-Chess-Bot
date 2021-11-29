import java.util.ArrayList;

public abstract class Piece {
    public int type;
    public int color;
    public int row;
    public int column;
    public int value;
    public static final int Pawn = 0;
    public static final int King = 1;
    public static final int Queen = 2;
    public static final int Knight = 3;
    public static final int Bishop = 4;
    public static final int Rook = 5;
    public static final int White = 0;
    public static final int Black = 1;

    public Piece (int type, int color, int row, int column, int value) {
        this.type = type;
        this.color = color;
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public abstract void move(ArrayList<String> moves, Piece[][] matrix);

    public abstract Piece makeCopy();
}

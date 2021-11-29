import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        boolean forceIsOff = true;
        int currentColor = Piece.Black;

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        String command = scanner.next();

        while (command.compareTo("stop") != 0) {
            // We send "feature" when receiving protover.
            if (command.contains("protover")) {
                System.out.println("feature sigint=0 san=0 name=Pharaoh");
            }

            // Exit game at "quit".
            else if (command.equals("quit") || command.equals("exit")) {
                break;
            }

            // Reinitialize board at "new".
            else if (command.equals("new")) {
                board.reinitialize();
                currentColor = Piece.Black;
                forceIsOff = true;
            }

            // Restart bot at "go"
            else if (command.equals("go")) {
                forceIsOff = true;
                board.nextMove(currentColor);
            }

            /* We check if we receive a valid move command and update the
            internal representation of the board.
             */
            else if (command.length() == 4 && command.charAt(0) >= 'a' && command.charAt(0) <= 'h' &&
                    command.charAt(2) >= 'a' && command.charAt(2) <= 'h' &&
                    command.charAt(1) >= '0' && command.charAt(1) <= '8' &&
                    command.charAt(3) >= '0' && command.charAt(3) <= '8') {
                board.update(command);

                if (forceIsOff) {
                    board.nextMove(currentColor);
                }
            }

            else if (command.length() == 5 && command.charAt(0) >= 'a' && command.charAt(0) <= 'h' &&
                    command.charAt(2) >= 'a' && command.charAt(2) <= 'h' &&
                    command.charAt(1) >= '0' && command.charAt(1) <= '8' &&
                    command.charAt(3) >= '0' && command.charAt(3) <= '8' &&
                    (command.charAt(4) == 'q' || command.charAt(4) == 'r' ||
                    command.charAt(4) == 'n' || command.charAt(4) == 'b')) {
                board.update(command);

                if (forceIsOff) {
                    board.nextMove(currentColor);
                }
            }

            // Print the internal board (for debug).
            else if (command.equals("print")) {
                board.print();
            }

            // Pause the bot at "force".
            else if (command.equals("force")) {
                forceIsOff = false;
            }

            // Change bot mode.
            else if (command.equals("black")) {
                currentColor = Piece.Black;
            }

            else if (command.equals("white")) {
                currentColor = Piece.White;
            }

            // System.out.println("DE AICI: " + command);
            command = scanner.next();
        }

        scanner.close();
    }
}
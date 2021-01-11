/*
 * Maggie Li
 * period 6
 * 12/6/18
 * This part of the lab (Activity 6) took me 30 minutes to complete.
 * 
 * 12/9/18
 * This part of the lab (Activity 7) took me 2 hours to complete.
 * At first, the code I was using did not consistently bring up the "Save As" 
 * window, but after I got further instructions for MacOS computers, I added 
 * more code that made sure that the "Save As" window always comes up when 
 * called. I was also not sure in what format to save the Board to a file. I 
 * figured that it would be easier to start from the bottom, by returning the 
 * state of each Card as a String, then using that to return the state of each 
 * Deck, and then finally using that to create a file with the state of the 
 * Board. Because I knew the exact format of how I saved the state of the
 * Board to the file, I was able to easily write a method that reversed that
 * to restore the saved Board. Overall, while this lab was easily the hardest
 * and most complicated of the semester, it is also the most rewarding because
 * it forced me to review everything we learned this semester and because I am
 * now able to play a game that I wrote myself! Next time, I would like to
 * use the Card and Deck methods I made and try to create a different game.
 */
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
public class SpiderSolitaire
{
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 7;

    /** Number of complete decks used in the game.  
     *  The number of cards in a deck depends on the
     *  type of Card used.  For example, a 1-suit deck
     *  of standard playing cards consists of only 13 cards
     *  whereas a 4-suit deck consists of 52 cards.
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;

        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");
            String command = input.next();

            if (command.equals("move")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                String symbol = input.next();
                try {
                    int sourceStack = input.nextInt();
                    int destinationStack = input.nextInt();
                    board.makeMove(symbol, sourceStack, destinationStack);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid command.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Stack not found.");
                }
            }
            else if (command.equals("draw")) {
                board.drawCards();
            }
            else if (command.equals("clear")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                int sourceStack = input.nextInt();
                try {
                    board.clear(sourceStack);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid command.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Source stack not found.");
                }
            }
            else if (command.equals("restart")) {
                board = new Board(NUM_STACKS, NUM_DECKS);
            }
            else if (command.equals("save")) {
                board.saveBoard();
            }
            else if (command.equals("load")) {
                board.restoreBoard();
            }
            else if (command.equals("quit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            else {
                System.out.println("Invalid command.");
            }

            board.printBoard();

            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}

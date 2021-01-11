/*
 * Maggie Li
 * period 6
 * 12/6/18
 * This part of the lab (Activities 4-5) took me 4 hours to complete.
 */
import java.util.ArrayList;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import java.lang.reflect.InvocationTargetException;
import java.io.*;
import java.util.Scanner;
public class Board
{   
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes
    ArrayList<Deck> decks;
    ArrayList<Deck> completed;
    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  The number of Cards in a Deck
     *  depends on the number of suits. Here are examples:
     *  
     *  # suits     # numDecks      #cards in overall Deck
     *      1            1          13 (all same suit)
     *      1            2          26 (all same suit)
     *      2            1          26 (one of each suit)
     *      2            2          52 (two of each suit)
     *      4            2          104 (two of each suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.  If you'd like to specify
     *  more than one suit, feel free to add to the parameter list.
     */    
    public Board(int numStacks, int numDecks) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        decks = new ArrayList<Deck>();
        completed = new ArrayList<Deck>();
        Deck deck = new Deck();
        int numOfStacks = numStacks;
        while (numDecks > 0) {
            int i = 0;
            String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
            int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
            while (i < symbols.length) {
                deck.add(new Card(symbols[i], values[i]));
                i++;
            }
            numDecks--;
        }
        deck.shuffle();
        Deck drawPile = new Deck();
        decks.add(drawPile);
        deck.transferAll(drawPile, deck.numCards()/2);
        int numOfCards = deck.numCards();
        while (numStacks > 0) {
            decks.add(new Deck());
            numStacks--;
        }
        while (deck.numCards() > 0) {
            int k = 1;
            while (deck.numCards() > 0 && k < decks.size()) {
                decks.get(k).add(deck.getCard(0));
                deck.removeCard(0);
                k++;
            }
        }
        int l = 1;
        while (l < decks.size()) {
            decks.get(l).getCard(decks.get(l).numCards()-1).setFaceUp(true);
            l++;
        }
    }

    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.  Change the parameter list to match
     *  your implementation of Card if you need to.
     */
    public void makeMove(String symbol, int src, int dest) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        Deck thisDeck = decks.get(src);
        Deck thatDeck = decks.get(dest);
        boolean validRun = false;
        int i = thisDeck.numCards()-1;
        while (i >= 0) {
            String cardSymbol = thisDeck.getCard(i).getSymbol();
            if (cardSymbol.equalsIgnoreCase(symbol)) {
                if (thisDeck.getCard(i).isFaceUp() == false) {
                    i = -1;
                    break;
                }
                else {
                    break;
                }
            }
            i--;
        }
        if (i < 0) {
            System.out.println("Card not found in stack.");
        }
        else {
            int iCopy = i;
            if (i == thisDeck.numCards()-1) {
                validRun = true;
            }
            while (i < thisDeck.numCards()-1) {
                if (thisDeck.getCard(i).compareTo(thisDeck.getCard(i+1)) == 1) {
                    validRun = true;
                }
                else {
                    validRun = false;
                    break;
                }
                i++;
            }
            if (validRun == false) {
                System.out.println("Move is illegal, this is not a run.");
            }
            else if (thatDeck.numCards() == 0 || thatDeck.getCard(thatDeck.numCards()-1).compareTo(thisDeck.getCard(iCopy)) == 1) {
                validRun = true;
                thisDeck.transferAll(thatDeck, iCopy);
                if (thisDeck.numCards() > 0) {
                    thisDeck.getCard(thisDeck.numCards()-1).setFaceUp(true);
                }
            }
            else {
                validRun = false;
                System.out.println("Move is illegal, moving to destination does not create a longer run.");
            }
        }
    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        boolean validMove = false;
        int k = 1;
        while(k < decks.size()) {
            if (decks.get(k).numCards() > 0) {
                validMove = true;
            }
            else {
                validMove = false;
                break;
            }
            k++;
        }
        int i = 1;
        if (validMove == true) {
            while (i < decks.size()) {
                if (decks.get(0).numCards() > 0) {
                    decks.get(0).getCard(decks.get(0).numCards()-1).setFaceUp(true);
                    decks.get(0).transferAll(decks.get(i), decks.get(0).numCards()-1);
                }
                i++;
            }
        }
        else {
            System.out.println("Invalid move, there must be at least one card in each stack before you draw.");
        }
    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        boolean empty = false;
        int i = 0;
        while (i < decks.size()) {
            if (decks.get(i).numCards() == 0) {
                empty = true;
            }
            else {
                empty = false;
                break;
            }
            i++;
        }
        return empty;
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        // if (sourceStack <= 0 || sourceStack >= decks.size()) {
        // System.out.println("Source stack not found.");
        // }
        if (decks.get(sourceStack).numCards() < 13) {
            System.out.println("Invalid move");
        }
        else if (decks.get(sourceStack).getCard(decks.get(sourceStack).numCards()-1).getValue() == 1) {
            int i = decks.get(sourceStack).numCards()-1;
            boolean validRun = false;
            while (i > decks.get(sourceStack).numCards()-13) {
                if (decks.get(sourceStack).getCard(i).isFaceUp() == true && decks.get(sourceStack).getCard(i-1).isFaceUp() == true && decks.get(sourceStack).getCard(i).compareTo(decks.get(sourceStack).getCard(i-1)) == -1) {
                    validRun = true;
                }
                else {
                    validRun = false;
                    break;
                }
                i--;
            }
            if (validRun == true) {
                completed.add(new Deck());
                decks.get(sourceStack).transferAll(completed.get(completed.size()-1), decks.get(sourceStack).numCards()-13);
                if (decks.get(sourceStack).numCards() > 0) {
                    decks.get(sourceStack).getCard(decks.get(sourceStack).numCards()-1).setFaceUp(true);
                }
            }
        }
        else {
            System.out.println("Invalid move.");
        }
    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        System.out.println("Stacks:");
        int i = 1;
        while (i < decks.size()) {
            System.out.print(i + ": ");
            System.out.println(decks.get(i));
            i++;
        }
        System.out.println();
        System.out.println("Draw Pile:");
        System.out.println(decks.get(0));
        System.out.println();
        System.out.println("Completed Stacks:");
        int k = 0;
        while (completed != null && k < completed.size()) {
            System.out.println(completed.get(k));
            k++;
        }
    }
    
    /**
     * Allows user to save the current state of the board to a file that they
     * choose.
     */
    public void saveBoard() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        JFileChooser chooser = new JFileChooser(".");
                        chooser.showSaveDialog(null);
                        File savedBoard = chooser.getSelectedFile();
                        try {
                            FileWriter fw = new FileWriter(savedBoard);
                            int i = 0;
                            String numOfDecks = "" + decks.size();
                            fw.write(numOfDecks);
                            fw.write("\n");
                            while (i < decks.size()) {
                                fw.write(decks.get(i).saveDeck());
                                i++;
                                fw.write("\n");
                            }
                            i = 0;
                            while (i < completed.size()) {
                                fw.write(completed.get(i).saveDeck());
                                i++;
                            }
                            fw.close();
                        } catch (IOException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                });
        }
        catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InvocationTargetException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Allows user to restore the board to a previous saved state of a board by choosing a 
     * file of a previously saved board.
     */
    public void restoreBoard() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        JFileChooser chooser = new JFileChooser(".");
                        chooser.showOpenDialog(null);
                        File savedBoard = chooser.getSelectedFile();
                        try {
                            while (decks.size() > 0) {
                                decks.remove(0);
                            }
                            while (completed.size() > 0) {
                                completed.remove(0);
                            }
                            Scanner scan = new Scanner(savedBoard);
                            int numDecks = Integer.parseInt(scan.nextLine());
                            while (numDecks > 0) {
                                String nextDeck = scan.nextLine();
                                decks.add(new Deck(nextDeck));
                                numDecks--;
                            }
                            while (scan.hasNextLine()) {
                                String nextCompletedDeck = scan.nextLine();
                                completed.add(new Deck(nextCompletedDeck));
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                });
        }
        catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InvocationTargetException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
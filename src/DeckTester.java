public class DeckTester
{
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
        int i = 0;
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        Deck deck = new Deck();
        while (i < symbols.length) {
            deck.add(new Card(symbols[i], values[i]));
            deck.getCard(i).setFaceUp(true);
            i++;
        }

        System.out.println("This deck has " + deck.numCards() + " cards");
        System.out.println("This deck contains: " + deck);
        deck.shuffle();
        System.out.println("This deck contains: " + deck);
        deck.sort();
        System.out.println("This deck contains: " + deck);
        
		/*
		 * System.out.println(deck.saveDeck()); Deck newDeck = new
		 * Deck(deck.saveDeck()); System.out.println(newDeck);
		 * 
		 * Board board = new Board(5, 2); board.saveBoard();
		 * 
		 * Board board2 = new Board(4, 1);
		 * 
		 * board2.restoreBoard(); board2.printBoard();
		 */
    }
}
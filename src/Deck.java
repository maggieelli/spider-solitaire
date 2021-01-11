import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class Deck
{
    /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
    ArrayList<Card> deck;

    /**
     * Creates a new <code>Deck</code> instance.
     */
    public Deck() {
        deck = new ArrayList<Card>();
    }

    /**
     * Creates a new <code>Deck</code> instance using a String that is returned from
     * calling the <code>saveDeck()</code> method on a Deck.
     */
    public Deck(String savedDeck) {
        Scanner scan = new Scanner(savedDeck);
        String nCards = scan.next();
        int numCards = Integer.parseInt(nCards);
        deck = new ArrayList<Card>();
        int i = 0;
        while (scan.hasNext()) {
            deck.add(new Card(scan.next(), scan.nextInt()));
            if (scan.nextBoolean() == false) {
                i++;
            }
            else {
                deck.get(i).setFaceUp(true);
                i++;
            }
        }
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        int i = 0;
        int size = deck.size();
        while (i < size) {
            Random rand = new Random();
            int k = rand.nextInt(size);
            Card ogI = deck.get(i);
            deck.set(i, deck.get(k));
            deck.set(k, ogI);
            i++;
        }
    }

    /**
     * Returns this Deck as a String starting from the "bottom" card to the "top card."
     * If the card is face down, an X will be shown instead of the card's symbol.
     * 
     * @return a <code>String</code> containing the symbols of each card in the
     * Deck that is face up, and an X of each card that is face down.
     */
    @Override
    public String toString() {
        String x = "[";
        int i = 0;
        while (i < deck.size()) {
            if (deck.get(i).isFaceUp() == false) {
                x += "X";
            }
            else {
                x += deck.get(i).getSymbol();
            }
            if (i != deck.size() -1) {
                x += ", ";
            }
            i++;
        }
        x += "]";
        return x;
    }

    /**
     * Adds a Card that is passed in to the Deck.
     * 
     * @param a Card that will be added to the Deck
     */
    public void add(Card card) {
        deck.add(card);
    }

    /**
     * Returns a Card at a given index.
     * 
     * @param index of Card that will be returned
     * @return a Card at the index passed in
     */
    public Card getCard(int index) {
        return deck.get(index);
    }

    /**
     * Removes a Card at a given index.
     * 
     * @param index of Card that will be removed
     */
    public void removeCard(int index) {
        deck.remove(index);
    }

    /**
     * Tranfers all Cards starting from an index passed in to another Deck passed in.
     * 
     * @param toDeck Deck to which all the Cards will be tranferred
     * @param index all cards from this index onward will be transferred
     */
    public void transferAll(Deck toDeck, int index) {
        int i = index;
        while (i < numCards()) {
            toDeck.add(this.getCard(i));
            removeCard(i);
        }
    }

    /**
     * Returns the number of Cards in the Deck.
     * 
     * @return the number of Cards in the Deck.
     */
    public int numCards() {
        return deck.size();
    }

    /**
     * Saves the current state of the Deck.
     * 
     * @return String containing the number of Cards in the Deck and all the Cards' 
     * symbols, values, and whether they are face up or face down
     */
    public String saveDeck() {
        String x = "";
        String numOfCards = String.valueOf(numCards());
        x += numOfCards + " ";
        int i = 0;
        while (i < deck.size()) {
            x += deck.get(i).saveString();
            i++;
        }
        return x;
    }
    
    public void sort() {
    	int len = deck.size();
		int k = 0;
		while (k < len-1) {
			int i = 0;
			int maxIndex = 0;
			while (i < len-k) {
				if (deck.get(i).compareTo(deck.get(maxIndex)) > 0) {
					maxIndex = i;
				}
				i++;
			}
			Card ogMax = deck.get(maxIndex);
			deck.set(maxIndex, deck.get(len-(k+1)));
			deck.set(len-(k+1), ogMax);
			k++;
		}
    }
}

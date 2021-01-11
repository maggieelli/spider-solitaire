/*
 * Maggie Li
 * period 6
 * 12/2/18
 * This part of the lab (Activities 1-3) took me 1.5 hours to complete.
 * In the first 3 activities,I had to create a Card class and a Deck class that could
 * be used for any card game. At first I had trouble deciding what to use in the Deck
 * class to hold all the cards, but I ultimately decided that using an ArrayList would
 * be the best option. I put an ArrayList as an attribute and then initialized
 * it in the constructor. I then had to add an add() method to the class so that cards
 * could be added to the ArrayList. Writing the shuffle() method was somewhat 
 * challenging, but I accomplished it using the Random class to switch cards randomly.
 * Next time, I would like to add more methods to the Deck class.
 */
/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String sym;

    /** int value that holds the value this card is worth */
    private int val;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        sym = symbol;
        val = value;
        isFaceUp = false;
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return sym;
    }

    /**
     * Getter method that returns value of card.
     * 
     * @return value of card
     */
    public int getValue() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return val;
    }

    /**
     * Returns true if card is face up, false if it is face down.
     * 
     * @return boolean with true if card is face up and false if it is face down
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Setter method to set the card to be face up or face down.
     * 
     * @param boolean with true if card is face up and false if it is face down
     */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     *  @param other the Card object that will be compared to.
     *  @return whether or not this Card is equal to other.
     */
    public boolean equals(Card other) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        if (getValue() == other.getValue()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the difference in value between this Card and the Card 
     * being passed in as a parameter. Returns a negative integer if the
     * card value is less than that of the other card passed in, and returns 
     * a positive integer if it card value is greater than that of the other
     * card passed in.
     * 
     * @param other the Card object that will be compared to.
     * @return the difference in value between this Card and other.
     */
    public int compareTo(Card other) {
        if (equals(other)) {
            return 0;
        }
        else {
            return getValue() - other.getValue();
        }
    }
    
    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol and point
     *         value of the card.
     */
    @Override
    public String toString() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        if (isFaceUp == false) {
            return "X";
        }
        else {
            String x = "";
            return x + getSymbol() + " " + getValue();
        }
    }
    
    /**
     * Saves the current state of the Card (symbol, value, and whether it is face up)
     * 
     * @return String containing the Card's symbol, value, true/false depending on
     * whether it is face up or face down
     */
    public String saveString() {
        String x = "";
        x += getSymbol() + " " + getValue() + " ";
        if (isFaceUp() == true) {
            x += "true ";
        }
        else {
            x += "false ";
        }
        return x;
    }
}

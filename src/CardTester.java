public class CardTester
{
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        Card card1 = new Card("7", 7);
        System.out.println(card1.getSymbol());
        System.out.println(card1.getValue());
        System.out.println(card1.isFaceUp());
        System.out.println(card1.toString());
        card1.setFaceUp(true);
        System.out.println(card1.toString());
        
        System.out.println();
        
        Card card2 = new Card("Q", 11);
        System.out.println(card2.getSymbol());
        System.out.println(card2.getValue());
        System.out.println(card2.isFaceUp());
        System.out.println(card2.toString());
        card2.setFaceUp(true);
        System.out.println(card2.toString());
        System.out.println(card2.equals(card1));
        System.out.println(card2.compareTo(card1));
        
        System.out.println();
        
        Card card3 = new Card("Q", 11);
        System.out.println(card3.getSymbol());
        System.out.println(card3.getValue());
        System.out.println(card3.isFaceUp());
        System.out.println(card3.toString());
        card3.setFaceUp(true);
        System.out.println(card3.toString());
        System.out.println(card3.equals(card2));
        System.out.println(card3.compareTo(card2));
    }
}

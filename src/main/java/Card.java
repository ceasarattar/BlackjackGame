public class Card {
    String face;
    String suit;
    int value;
    Card(String theFace, String theSuit, int theValue){
        this.face = theFace; //K
        this.suit = theSuit; //Spade
        this.value = theValue; //10
    }
    // Getter for the face field
    public String getFace() {
        return face;
    }

    // Getter for the suit field
    public String getSuit() {
        return suit;
    }

    // Getter for the value field
    public int getValue() {
        return value;
    }
}
